import SwiftUI
import Shared
import Combine

struct LoginView: View {
    @ObservedObject var viewModel: LoginViewModelWrapper
    let navigation: LoginNavigation

    var body: some View {
        ZStack {
            VStack(spacing: 24) {
                Text("Cosmic APP")
                    .font(.system(size: 32, weight: .bold))
                
                if let error = viewModel.state.error {
                    Text(error)
                        .foregroundColor(.red)
                        .font(.caption)
                }

                VStack(spacing: 16) {
                    Button(action: {
                        viewModel.onEvent(event: LoginEventSignInWithApple())                     }) {
                        HStack {
                            Image(systemName: "applelogo")
                            Text("Sign in with Apple")
                        }
                        .frame(maxWidth: .infinity)
                        .padding()
                        .background(Color.black)
                        .foregroundColor(.white)
                        .cornerRadius(12)
                    }
                }
                .padding(.horizontal, 32)
            }
            .disabled(viewModel.state.isLoading)
            
            if viewModel.state.isLoading {
                Color.black.opacity(0.2)
                    .edgesIgnoringSafeArea(.all)
                ProgressView()
                    .scaleEffect(1.5)
            }
        }
        .onReceive(viewModel.effects) { effect in
            if effect is LoginEffectNavigateToDashboard {
                navigation.onLoginSuccess()
            }
        }
    }
}

class LoginViewModelWrapper: ObservableObject {
    private let viewModel: LoginViewModel
    @Published var state: LoginState
    var effects = PassthroughSubject<LoginEffect, Never>()
    private var cancellables = Set<AnyCancellable>()

    init(viewModel: LoginViewModel) {
        self.viewModel = viewModel
        self.state = viewModel.state.value as! LoginState
        
        // Listen to state changes
       let stateSequence = SkieSwiftFlow<LoginState>(viewModel.state)
       Task {
           for await newState in stateSequence {
               await MainActor.run {
                   self.state = newState
               }
           }
       }
        
     //   Listen to effects
       let effectSequence = SkieSwiftFlow<LoginEffect>(viewModel.effects)
       Task {
           for await effect in effectSequence {
               await MainActor.run {
                   self.effects.send(effect)
               }
           }
       }
    }

    func onEvent(event: LoginEvent) {
        viewModel.onEvent(event: event)
    }
}


import SwiftUI
import shared

struct AuthView: View {
    let navigation: AuthNavigation // The Shared Kotlin Interface
    @ObservedObject var viewModel: AuthViewModel // Injected via Koin or Wrapper    let navigation: AuthNavigation // The Shared Kotlin Interface

    var body: some View {
        ZStack {
            if viewModel.state.isLoading {
                ProgressView()
            } else {
                VStack(spacing: 20) {
                    Text("CosmicAPP")
                        .font(.largeTitle)
                        .fontWeight(.bold)

                    Button(action: {
                          viewModel.onEvent(AuthViewEvent.LoginClicked())
                    }) {
                        Text("Sign in with Apple")
                            .frame(maxWidth: .infinity)
                            .padding()
                            .background(Color.black)
                            .foregroundColor(.white)
                            .cornerRadius(10)
                    }
                    .padding(.horizontal)
                }
            }
        }
        .onReceive(viewModel.effects) { effect in
            if effect is AuthEffectNavigateToDashboard {
                onNavigateToDashboard()
            } else if let error = effect as? AuthEffectShowError {
                // Handle showing error (e.g., alert)
                print("Error: \(error.message)")
            }
        }
    }
}

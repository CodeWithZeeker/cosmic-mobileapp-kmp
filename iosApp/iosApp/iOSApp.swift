import SwiftUI
import Shared

@main
struct iOSApp: App {
    init() {
        KoinIosKt.doInitKoinIos()
    }

    var body: some Scene {
        WindowGroup {
            LaunchVew()
        }
    }
}

struct LaunchVew: View {
    @State private var isAuthenticated = false

    var body: some View {
        if isAuthenticated {
            ContentView()
        } else {
            let viewModel = KoinHelper.shared.getLoginViewModel()
            let wrapper = LoginViewModelWrapper(viewModel: viewModel)
            LoginView(viewModel: wrapper, navigation: MainCoordinator())
        }
    }

  
}

class MainCoordinator: LoginNavigation {
    func onLoginSuccess() {
        withAnimation {
        }
    }

    func onSignUpClicked() {
        // Handle sign up navigation if needed
    }
}

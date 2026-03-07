I am building a KMP project named CosmicAPP. We are using a Feature-Oriented MVI Architecture.

Shared Logic: Everything goes in commonMain using Koin for DI and Ktor for Network.

UI Strategy: Use SwiftUI for the iosApp and Jetpack Compose for the androidApp.

State Management: Every feature must have a ViewState (State), ViewEvent (Intents), and ViewEffect (One-time events like navigation).

Module Rules: We use a nested structure: shared/foundation, shared/app, and features/ for domain modules.
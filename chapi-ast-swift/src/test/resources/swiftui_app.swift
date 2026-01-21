// SwiftUI App Example
import SwiftUI
import Combine

// Main App
@main
struct MyApp: App {
    @StateObject private var appState = AppState()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
                .environmentObject(appState)
        }
    }
}

// App State
class AppState: ObservableObject {
    @Published var isLoggedIn: Bool = false
    @Published var currentUser: User?
    
    func login(username: String, password: String) async throws {
        // Login logic
        let user = try await AuthService.login(username: username, password: password)
        await MainActor.run {
            self.currentUser = user
            self.isLoggedIn = true
        }
    }
}

// User Model
struct User: Codable, Identifiable, Sendable {
    let id: UUID
    let username: String
    let email: String
    var profile: UserProfile?
}

struct UserProfile: Codable, Sendable {
    var displayName: String
    var avatarURL: URL?
    var bio: String?
}

// View
struct ContentView: View {
    @EnvironmentObject var appState: AppState
    @State private var username: String = ""
    @State private var password: String = ""
    @State private var showError: Bool = false
    @State private var errorMessage: String = ""
    
    var body: some View {
        if appState.isLoggedIn {
            HomeView()
        } else {
            loginView
        }
    }
    
    private var loginView: some View {
        VStack(spacing: 20) {
            TextField("Username", text: $username)
                .textFieldStyle(.roundedBorder)
            
            SecureField("Password", text: $password)
                .textFieldStyle(.roundedBorder)
            
            Button("Login") {
                Task {
                    await performLogin()
                }
            }
            .buttonStyle(.borderedProminent)
        }
        .padding()
        .alert("Error", isPresented: $showError) {
            Button("OK") { }
        } message: {
            Text(errorMessage)
        }
    }
    
    private func performLogin() async {
        do {
            try await appState.login(username: username, password: password)
        } catch {
            errorMessage = error.localizedDescription
            showError = true
        }
    }
}

struct HomeView: View {
    @EnvironmentObject var appState: AppState
    @State private var posts: [Post] = []
    
    var body: some View {
        NavigationStack {
            List(posts) { post in
                PostRow(post: post)
            }
            .navigationTitle("Home")
            .refreshable {
                await loadPosts()
            }
        }
        .task {
            await loadPosts()
        }
    }
    
    private func loadPosts() async {
        do {
            posts = try await PostService.fetchPosts()
        } catch {
            print("Error loading posts: \(error)")
        }
    }
}

struct PostRow: View {
    let post: Post
    
    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            Text(post.title)
                .font(.headline)
            Text(post.content)
                .font(.body)
                .lineLimit(3)
        }
    }
}

// Post Model
struct Post: Codable, Identifiable, Sendable {
    let id: UUID
    let title: String
    let content: String
    let authorId: UUID
    let createdAt: Date
}

// Services
actor AuthService {
    static let shared = AuthService()
    
    private var cachedUser: User?
    
    func login(username: String, password: String) async throws -> User {
        // API call
        let user = User(
            id: UUID(),
            username: username,
            email: "\(username)@example.com",
            profile: nil
        )
        cachedUser = user
        return user
    }
    
    func logout() async {
        cachedUser = nil
    }
}

actor PostService {
    static let shared = PostService()
    
    func fetchPosts() async throws -> [Post] {
        // API call
        return []
    }
}

// Custom Property Wrapper
@propertyWrapper
struct Clamped<Value: Comparable> {
    private var value: Value
    private let range: ClosedRange<Value>
    
    init(wrappedValue: Value, _ range: ClosedRange<Value>) {
        self.range = range
        self.value = min(max(wrappedValue, range.lowerBound), range.upperBound)
    }
    
    var wrappedValue: Value {
        get { value }
        set { value = min(max(newValue, range.lowerBound), range.upperBound) }
    }
}

struct GameCharacter {
    @Clamped(0...100) var health: Int = 100
    @Clamped(0...10) var level: Int = 1
}

// Generic Protocol with Associated Type
protocol Repository {
    associatedtype Entity
    
    func fetch(id: UUID) async throws -> Entity
    func save(_ entity: Entity) async throws
    func delete(id: UUID) async throws
}

actor UserRepository: Repository {
    typealias Entity = User
    
    func fetch(id: UUID) async throws -> User {
        // Implementation
        fatalError("Not implemented")
    }
    
    func save(_ entity: User) async throws {
        // Implementation
    }
    
    func delete(id: UUID) async throws {
        // Implementation
    }
}

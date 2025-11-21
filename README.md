# 🌍 Mazen World (عالم مازن)

**Mazen World** is a modern, cross-platform educational application designed to provide an engaging learning experience for children. Built with **Kotlin Multiplatform (KMP)** and **Compose Multiplatform**, it targets Android, iOS, and Web from a single codebase, ensuring a consistent and high-quality user experience across all devices.

---

## 🚀 Key Features

*   📱 **Cross-Platform Support**: Runs natively on Android, iOS, and Web (Wasm/JS).
*   🎨 **Modern UI/UX**: Built with **Jetpack Compose** and **Compose Multiplatform** for a declarative and beautiful UI.
*   🏗️ **Clean Architecture**: Structured using Clean Architecture principles (Data, Domain, Presentation) for scalability and testability.
*   ☁️ **Cloud Integration**: Powered by **Firebase** and **Supabase** for reliable backend services.
*   🌐 **Robust Networking**: Uses **Ktor** for efficient network operations.
*   💉 **Dependency Injection**: Leverages **Koin** for lightweight and pragmatic dependency injection.

---

## 🛠️ Tech Stack

*   🐶 **Language**: [Kotlin](https://kotlinlang.org/)
*   🎹 **UI Framework**: [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
*   🏛️ **Architecture**: Clean Architecture + MVVM
*   🪙 **Dependency Injection**: [Koin](https://insert-koin.io/)
*   🔌 **Networking**: [Ktor](https://ktor.io/)
*   🖼️ **Image Loading**: [Coil 3](https://coil-kt.github.io/coil/)
*   🔥 **Backend**:
    *   [Firebase](https://firebase.google.com/) (Firestore)
    *   [Supabase](https://supabase.com/) (PostgREST)
*   🧭 **Navigation**: [Navigation Compose](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
*   🎬 **Animations**: [Lottie](https://airbnb.io/lottie/)

---

## 📂 Project Structure

The project follows a standard Kotlin Multiplatform structure:

*   📦 **`composeApp`**: The main module containing shared code and platform-specific entry points.
    *   🔹 `commonMain`: Shared code (UI, Domain, Data) used by all platforms.
    *   🤖 `androidMain`: Android-specific implementations.
    *   🍎 `iosMain`: iOS-specific implementations.
*   🍏 **`iosApp`**: The iOS Xcode project entry point.

---

## 🏁 Getting Started

### Prerequisites

*   ☕ **JDK 17** or higher.
*   🤖 **Android Studio** (latest stable or canary for KMP support).
*   🍎 **Xcode** (for iOS development, macOS only).
*   📦 **CocoaPods** (for iOS dependency management).

### Installation

1.  📥 **Clone the repository**:
    ```bash
    git clone https://github.com/your-username/mazen-world.git
    cd mazen-world
    ```

2.  💻 **Open in Android Studio**:
    Open the project root directory in Android Studio and let Gradle sync.

---

## 🏃‍♂️ Build & Run

### 🤖 Android

Run the app directly from Android Studio using the `composeApp` run configuration, or use the terminal:

```bash
./gradlew :composeApp:assembleDebug
```

### 🍎 iOS

1.  Open `iosApp/iosApp.xcworkspace` in **Xcode**.
2.  Select your target simulator or device.
3.  Run the app (**Cmd + R**).

Alternatively, run from Android Studio if the KMP plugin is configured.

### 🌐 Web

#### Wasm (Recommended for modern browsers)
```bash
./gradlew :composeApp:wasmJsBrowserDevelopmentRun
```

#### JS (Legacy support)
```bash
./gradlew :composeApp:jsBrowserDevelopmentRun
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1.  🍴 Fork the repository.
2.  🌿 Create a feature branch (`git checkout -b feature/amazing-feature`).
3.  💾 Commit your changes (`git commit -m 'Add some amazing feature'`).
4.  🚀 Push to the branch (`git push origin feature/amazing-feature`).
5.  🔀 Open a Pull Request.

---

## 📄 License

This project is licensed under the [MIT License](LICENSE).
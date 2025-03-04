This is a Kotlin Multiplatform project targeting Android, iOS, Desktop.

https://github.com/user-attachments/assets/da8e6c2d-d07c-4b1d-9749-c46798aa6094

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
    - `commonMain` is for code that’s common for all targets.
    - Other folders are for Kotlin code that will be compiled for only the platform indicated in the
      folder name.
      For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
      `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform,
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for
  your project.

Currently, the application is not optimized for desktop and few features are yet to complete for iOS.

Learn more
about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

Architecture: MVI
Presentation -> Domain <- Data

Todos:

1. Move loader to lottie
2. Download comic books for pokemon
3. Play 1v1 multiplayer game
4. Audio and gif of the pokemon
5. In listing screen, when in favourite section, on back press it should move to first section.
   Currently, the app closes on back press in favourite section.
6. For the special action button in 1v1, change its implementation to bitmap to reduce
   recomposition (refer PokeVerse repo)
7. Hoist game over screen
8. Center loader while loading next page

NOTE: This project is currently on going, and is not well optimized for desktop and iOS.

package org.example.pokeverse

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.example.pokeverse.di.initKoin

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = "PokeVerse",
        ) {
            App()
        }
    }
}
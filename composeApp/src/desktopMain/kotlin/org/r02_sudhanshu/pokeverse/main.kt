package org.r02_sudhanshu.pokeverse

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import org.r02_sudhanshu.pokeverse.app.App
import org.r02_sudhanshu.pokeverse.di.initKoin

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
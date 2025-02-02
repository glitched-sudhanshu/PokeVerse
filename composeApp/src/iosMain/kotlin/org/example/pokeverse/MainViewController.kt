package org.example.pokeverse

import androidx.compose.ui.window.ComposeUIViewController
import org.example.pokeverse.app.App
import org.example.pokeverse.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
package org.r02_sudhanshu.pokeverse

import androidx.compose.ui.window.ComposeUIViewController
import org.r02_sudhanshu.pokeverse.app.App
import org.r02_sudhanshu.pokeverse.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}
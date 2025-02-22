package org.example.pokeverse.arena.presentation.components

import platform.UIKit.UIScreen

actual fun Float.dpToPx(): Float = this * UIScreen.mainScreen.scale.toFloat()


package org.r02_sudhanshu.pokeverse.arena.presentation.components

import platform.UIKit.UIScreen

actual fun Float.dpToPx(): Float = this * UIScreen.mainScreen.scale.toFloat()


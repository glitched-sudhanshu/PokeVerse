package org.r02_sudhanshu.pokeverse.arena.presentation.components

import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectGetWidth
import platform.UIKit.UIScreen

@OptIn(ExperimentalForeignApi::class)
actual val screenWidth: Float
    get() = CGRectGetWidth(UIScreen.mainScreen.bounds).toFloat()

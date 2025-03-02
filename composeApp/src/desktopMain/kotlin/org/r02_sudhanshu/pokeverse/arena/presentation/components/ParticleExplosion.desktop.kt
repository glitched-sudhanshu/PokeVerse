package org.r02_sudhanshu.pokeverse.arena.presentation.components

import java.awt.Toolkit

actual fun Float.dpToPx(): Float {
    val screenResolution = Toolkit.getDefaultToolkit().screenResolution // DPI
    val density = screenResolution / 96f // Convert DPI to density (96 DPI = 1x)
    return this * density
}
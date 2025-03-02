package org.r02_sudhanshu.pokeverse.arena.presentation.components

import java.awt.Toolkit

actual val screenWidth: Float
    get() = Toolkit.getDefaultToolkit().screenSize.width.toFloat()

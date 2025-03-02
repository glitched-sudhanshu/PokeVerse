package org.r02_sudhanshu.pokeverse.arena.presentation.components

import android.content.res.Resources

actual val screenWidth: Float
    get() = Resources.getSystem().displayMetrics.widthPixels.toFloat()
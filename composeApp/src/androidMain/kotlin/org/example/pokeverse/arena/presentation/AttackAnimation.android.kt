package org.example.pokeverse.arena.presentation

import android.content.res.Resources

actual val screenWidth: Float
    get() = Resources.getSystem().displayMetrics.widthPixels.toFloat()
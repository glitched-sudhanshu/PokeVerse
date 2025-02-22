package org.example.pokeverse.arena.presentation

import android.content.res.Resources

actual fun Float.dpToPx(): Float = this * Resources.getSystem().displayMetrics.density
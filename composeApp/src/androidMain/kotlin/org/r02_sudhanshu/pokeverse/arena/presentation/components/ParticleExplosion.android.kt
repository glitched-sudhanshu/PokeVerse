package org.r02_sudhanshu.pokeverse.arena.presentation.components

import android.content.res.Resources

actual fun Float.dpToPx(): Float = this * Resources.getSystem().displayMetrics.density
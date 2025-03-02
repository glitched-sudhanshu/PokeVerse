package org.r02_sudhanshu.pokeverse.arena.presentation

import android.app.Activity
import android.content.pm.ActivityInfo
import org.koin.java.KoinJavaComponent.getKoin

actual fun setScreenOrientationLandscape() {
    getKoin().getOrNull<Activity>()?.requestedOrientation =
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
}

actual fun setScreenOrientationPortrait() {
    getKoin().getOrNull<Activity>()?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
}
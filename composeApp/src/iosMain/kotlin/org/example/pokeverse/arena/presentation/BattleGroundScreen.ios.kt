package org.example.pokeverse.arena.presentation

import androidx.compose.runtime.Composable
import platform.Foundation.setValue
import platform.UIKit.UIDevice
import platform.UIKit.UIInterfaceOrientationLandscapeLeft
import platform.UIKit.UIInterfaceOrientationPortrait

actual fun setScreenOrientationLandscape() {
    UIDevice.currentDevice.setValue(UIInterfaceOrientationLandscapeLeft, forKey = "orientation")
}

actual fun setScreenOrientationPortrait() {
    UIDevice.currentDevice.setValue(UIInterfaceOrientationPortrait, forKey = "orientation")
}

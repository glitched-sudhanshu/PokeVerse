package org.r02_sudhanshu.pokeverse.arena.presentation

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

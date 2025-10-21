package com.aslmmovic.mazenworld.utils

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import platform.Foundation.NSUserDefaults

// iosMain/utils/SettingsFactory.ios.kt

actual class SettingsFactory {
    actual fun createSettings(): Settings {
        return NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
    }
}
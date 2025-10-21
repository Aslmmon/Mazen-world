package com.aslmmovic.mazenworld.utils

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings

// androidMain/utils/SettingsFactory.android.kt

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): Settings {
        return SharedPreferencesSettings(
            delegate = context.getSharedPreferences(
                "app_settings",
                Context.MODE_PRIVATE
            )
        )
        // Or use SharedPreferencesSettings if not using DataStore
    }
}
package com.aslmmovic.mazenworld.utils

import com.russhwolf.settings.Settings


expect class SettingsFactory {
    fun createSettings(): Settings
}
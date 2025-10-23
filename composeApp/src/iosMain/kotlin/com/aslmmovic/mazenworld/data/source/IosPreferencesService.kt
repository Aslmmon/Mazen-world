// iosMain/data/source/IosPreferencesService.kt

package com.aslmmovic.mazenworld.data.source // MATCH YOUR PACKAGE

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import platform.Foundation.NSUserDefaults

// Implementation that wraps synchronous NSUserDefaults with Flow/Suspend
class IosPreferencesService : PreferencesService {

    private val settings: Settings = NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)

    // --- StateFlows to simulate DataStore's reactive behavior ---
    private val starsFlow = MutableStateFlow(settings.getInt("star_count", 0))
    private val musicFlow = MutableStateFlow(settings.getBoolean("music_enabled", true))
    private val soundFlow = MutableStateFlow(settings.getBoolean("sound_enabled", true))
    private val premiumFlow = MutableStateFlow(settings.getBoolean("is_premium", false))

    // --- Read Operations ---
    override fun getStars(): Flow<Int> = starsFlow
    override fun getMusicEnabled(): Flow<Boolean> = musicFlow
    override fun getSoundEnabled(): Flow<Boolean> = soundFlow
    override fun isPremium(): Flow<Boolean> = premiumFlow

    // --- Write Operations ---
    override suspend fun saveStars(stars: Int) {
        settings.putInt("star_count", stars)
        starsFlow.update { stars }
    }
    override suspend fun setMusicEnabled(enabled: Boolean) {
        settings.putBoolean("music_enabled", enabled)
        musicFlow.update { enabled }
    }
    override suspend fun setSoundEnabled(enabled: Boolean) {
        settings.putBoolean("sound_enabled", enabled)
        soundFlow.update { enabled }
    }
    override suspend fun setPremium(status: Boolean) {
        settings.putBoolean("is_premium", status)
        premiumFlow.update { status }
    }
}
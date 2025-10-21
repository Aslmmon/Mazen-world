package com.aslmmovic.mazenworld.data.source

import com.aslmmovic.mazenworld.domain.UserProfile
import com.aslmmovic.mazenworld.utils.SettingsFactory
import com.russhwolf.settings.Settings

// data/source/UserLocalDataSource.kt (commonMain)

class UserLocalDataSource(settingsFactory: SettingsFactory) {
    private val settings: Settings = settingsFactory.createSettings()

    // Keys for persistence
    private val STAR_COUNT_KEY = "STAR_COUNT"
    private val IS_PREMIUM_KEY = "IS_PREMIUM"
    private val SOUND_ENABLED_KEY = "SOUND_ENABLED"
    private val MUSIC_ENABLED_KEY = "MUSIC_ENABLED"

    fun loadProfile(): UserProfile {
        return UserProfile(
            stars = settings.getInt(STAR_COUNT_KEY, 0), // Default to 0
            isPremium = settings.getBoolean(IS_PREMIUM_KEY, false), // Default to false
            // Add other properties later
            soundEnabled = settings.getBoolean(SOUND_ENABLED_KEY, true),
            musicEnabled = settings.getBoolean(MUSIC_ENABLED_KEY, true)

        )
    }

    fun saveProfile(profile: UserProfile) {
        settings.putInt(STAR_COUNT_KEY, profile.stars)
        settings.putBoolean(IS_PREMIUM_KEY, profile.isPremium)
        settings.putBoolean(SOUND_ENABLED_KEY, profile.soundEnabled)
        settings.putBoolean(MUSIC_ENABLED_KEY, profile.musicEnabled)
    }
}
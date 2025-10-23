package com.aslmmovic.mazenworld.data.source

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// androidMain/data/source/AndroidPreferencesService.kt


// This is the implementation that was missing previously
class AndroidPreferencesService(private val dataStore: DataStore<Preferences>) :
    PreferencesService {

    // Define Preference Keys
    private val STAR_COUNT_KEY = intPreferencesKey("star_count")
    private val MUSIC_ENABLED_KEY = booleanPreferencesKey("music_enabled")
    private val SOUND_ENABLED_KEY = booleanPreferencesKey("sound_enabled")
    private val IS_PREMIUM_KEY = booleanPreferencesKey("is_premium")

    // --- Read Operations ---
    override fun getStars(): Flow<Int> = dataStore.data.map { it[STAR_COUNT_KEY] ?: 0 }
    override fun getMusicEnabled(): Flow<Boolean> =
        dataStore.data.map { it[MUSIC_ENABLED_KEY] ?: true }

    override fun getSoundEnabled(): Flow<Boolean> =
        dataStore.data.map { it[SOUND_ENABLED_KEY] ?: true }

    override fun isPremium(): Flow<Boolean> = dataStore.data.map { it[IS_PREMIUM_KEY] ?: false }

    // --- Write Operations ---
    override suspend fun saveStars(stars: Int) {
        dataStore.edit { it[STAR_COUNT_KEY] = stars }
    }

    override suspend fun setMusicEnabled(enabled: Boolean) {
        dataStore.edit { it[MUSIC_ENABLED_KEY] = enabled }
    }

    override suspend fun setSoundEnabled(enabled: Boolean) {
        dataStore.edit { it[SOUND_ENABLED_KEY] = enabled }
    }

    override suspend fun setPremium(status: Boolean) {
        dataStore.edit { it[IS_PREMIUM_KEY] = status }
    }
}
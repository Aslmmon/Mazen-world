package com.aslmmovic.mazenworld.utils

// commonMain/utils/AudioPlayer.kt

interface AudioPlayer {
    fun playSound(resourceId: String, volume: Float = 1.0f)
    fun startMusic(resourceId: String, loop: Boolean = false)
    fun pauseMusic()
    fun resumeMusic()
    fun stopAndRelease()
}

// Global accessor (expect/actual will provide the instance)
expect fun createAudioPlayer(): AudioPlayer

// We use an object to access the player globally.
// This simplifies calling sound functions from ViewModels or Composables.
object AudioPlayerManager : AudioPlayer by createAudioPlayer()
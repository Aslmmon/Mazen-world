package com.aslmmovic.mazenworld.utils

// commonMain/utils/AudioPlayer.kt

import org.jetbrains.compose.resources.Resource

/**
 * The expected API for the AudioPlayerManager.
 * The common code will use this interface to play sounds.
 * Platform-specific modules will provide the 'actual' implementation.
 */
expect class AudioPlayerManager {
    /**
     * Plays a short, one-off sound effect.
     * @param resource The compose resource of the sound to play.
     */
    fun playSoundEffect(resource: Resource)

    /**
     * Plays looping background music.
     * @param resource The compose resource of the music to play.
     */
    fun playBackgroundMusic(resource: ByteArray)

    /**
     * Stops the currently playing background music.
     */
    fun stopBackgroundMusic()

    /**
     * Releases all media player resources. Should be called when the app is destroyed.
     */
    fun release()
}


expect fun provideAudioPlayerManager(): AudioPlayerManager

package com.aslmmovic.mazenworld.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.jetbrains.compose.resources.Resource
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


lateinit var appContext: Context


actual class AudioPlayerManager : DefaultLifecycleObserver {

    private var backgroundMusicPlayer: MediaPlayer? = null
    private var backgroundMusicBytes: ByteArray? = null // Store the bytes to resume playback

    /**
     * Plays a short, one-off sound effect.
     * A new MediaPlayer instance is created and released for each effect.
     */
    actual fun playSoundEffect(resource: Resource) {
        try {
            val resId = getResourceId(resource)
            val mediaPlayer = MediaPlayer.create(appContext, resId)
            mediaPlayer.setOnCompletionListener { it.release() }
            mediaPlayer.start()
        } catch (e: IOException) {
            e.printStackTrace()
            // Handle error, e.g., log it
        }
    }

    /**
     * Plays looping background music.
     * It manages a single MediaPlayer instance for the background music.
     */
    actual fun playBackgroundMusic(resource: ByteArray) {
        if (backgroundMusicPlayer?.isPlaying == true) return // Don't restart if already playing

        this.backgroundMusicBytes = resource

        if (backgroundMusicPlayer == null) {
            try {
                val tempFile = createTempFileFromBytes(resource, "bgm")
                backgroundMusicPlayer = MediaPlayer().apply {
                    setDataSource(tempFile.absolutePath)
                    isLooping = true // Music should loop
                    setVolume(0.3f, 0.3f) // Lower volume for background music
                    prepare()
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle error
            }
        }
        if (backgroundMusicPlayer?.isPlaying == false) {
            backgroundMusicPlayer?.start()
        }
    }

    /**
     * Stops the currently playing background music.
     */
    actual fun stopBackgroundMusic() {
        if (backgroundMusicPlayer?.isPlaying == true) {
            backgroundMusicPlayer?.pause()
        }
    }

    /**
     * Releases all MediaPlayer resources.
     */
    actual fun release() {
        backgroundMusicPlayer?.release()
        backgroundMusicPlayer = null
    }

    /**
     * A helper function to get the Android-specific raw resource ID
     * from the KMP Resource object.
     */
    @RawRes
    private fun getResourceId(resource: Resource): Int {
        val path = resource.toString().substringAfter("files/").substringBeforeLast(".")
        return appContext.resources.getIdentifier(path, "raw", appContext.packageName)

    }

    private fun createTempFileFromBytes(bytes: ByteArray, prefix: String): File {
        val tempFile = File.createTempFile(prefix, ".tmp", appContext.cacheDir)
        tempFile.deleteOnExit()
        FileOutputStream(tempFile).use { fos ->
            fos.write(bytes)
        }
        return tempFile
    }


    override fun onStart(owner: LifecycleOwner) {
        // App came to the foreground, resume music
        if (backgroundMusicPlayer != null && backgroundMusicPlayer?.isPlaying == false) {
            backgroundMusicPlayer?.start()
        } else if (backgroundMusicPlayer == null && backgroundMusicBytes != null) {
            // If the player was released, recreate it and play
            playBackgroundMusic(backgroundMusicBytes!!)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        // App went to the background, pause music
        stopBackgroundMusic()
    }
}

private val audioPlayerManagerInstance: AudioPlayerManager by lazy {
    AudioPlayerManager()
}

actual fun provideAudioPlayerManager(): AudioPlayerManager {
    // Implement a singleton pattern here if needed
    return audioPlayerManagerInstance// Assuming your actual class is named this
}







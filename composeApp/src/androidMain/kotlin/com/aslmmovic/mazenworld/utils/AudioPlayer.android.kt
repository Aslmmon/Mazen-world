package com.aslmmovic.mazenworld.utils

import android.content.Context
import android.media.MediaPlayer
import androidx.annotation.RawRes
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.jetbrains.compose.resources.Resource
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.util.LinkedList
import java.util.Queue

lateinit var appContext: Context

actual class AudioPlayerManager : DefaultLifecycleObserver {

    private var backgroundMusicPlayer: MediaPlayer? = null
    private var backgroundMusicBytes: ByteArray? = null

    // --- 1. CREATE THE OBJECT POOL FOR SOUND EFFECTS ---
    private val soundEffectPlayerPool: Queue<MediaPlayer> = LinkedList()
    private val activeSoundEffectPlayers = mutableListOf<MediaPlayer>()
    private val maxPoolSize = 5 // Limit to 5 simultaneous sound effects


    private val _isMuted = MutableStateFlow(false)
    actual val isMuted: StateFlow<Boolean> = _isMuted.asStateFlow()


    private var hasMusicBeenStarted = false


    init {
        // Pre-fill the pool with a few MediaPlayer instances
        repeat(maxPoolSize) {
            soundEffectPlayerPool.add(MediaPlayer())
        }
    }

    actual fun toggleMute() {
        _isMuted.value = !_isMuted.value
        if (_isMuted.value) {
            backgroundMusicPlayer?.setVolume(0f, 0f) // Mute
        } else {
            backgroundMusicPlayer?.setVolume(0.3f, 0.3f) // Unmute to default volume
        }
    }


    /**
     * Plays a short, one-off sound effect using an object pool for efficiency.
     */

    actual fun playSoundEffect(resource: ByteArray) { // <-- 1. PARAMETER CHANGED
        val mediaPlayer = soundEffectPlayerPool.poll() ?: return

        try {
            // 2. USE THE SAME TEMP FILE LOGIC AS BACKGROUND MUSIC
            val tempFile = createTempFileFromBytes(resource, "sfx")

            mediaPlayer.apply {
                reset()
                setDataSource(tempFile.absolutePath) // 3. SET DATA SOURCE FROM TEMP FILE
                prepare()
                setOnCompletionListener { completedPlayer ->
                    completedPlayer.reset()
                    activeSoundEffectPlayers.remove(completedPlayer)
                    soundEffectPlayerPool.add(completedPlayer)
                    tempFile.delete() // 4. CLEAN UP THE TEMP FILE
                }
            }

            activeSoundEffectPlayers.add(mediaPlayer)
            mediaPlayer.start()

        } catch (e: IOException) {
            e.printStackTrace()
            // If something fails, return the player to the pool
            mediaPlayer.reset()
            soundEffectPlayerPool.add(mediaPlayer)
        }
    }


    // --- The rest of the class remains the same ---

    /**
     * Plays looping background music.
     */
    actual fun playBackgroundMusic(resource: ByteArray) {
        if (backgroundMusicPlayer?.isPlaying == true) return
        if (hasMusicBeenStarted) return
        this.backgroundMusicBytes = resource
        if (backgroundMusicPlayer == null) {
            try {
                val tempFile = createTempFileFromBytes(resource, "bgm")
                backgroundMusicPlayer = MediaPlayer().apply {
                    setDataSource(tempFile.absolutePath)
                    isLooping = true
                    val volume = if (_isMuted.value) 0f else 0.3f
                    setVolume(volume, volume)
                    prepare()
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        if (backgroundMusicPlayer?.isPlaying == false) {
            backgroundMusicPlayer?.start()
            hasMusicBeenStarted = true
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

        // 4. RELEASE ALL POOLED PLAYERS
        soundEffectPlayerPool.forEach { it.release() }
        soundEffectPlayerPool.clear()
        activeSoundEffectPlayers.forEach { it.release() }
        activeSoundEffectPlayers.clear()
    }

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
        if (backgroundMusicPlayer != null && backgroundMusicPlayer?.isPlaying == false) {
            backgroundMusicPlayer?.start()
        } else if (backgroundMusicPlayer == null && backgroundMusicBytes != null) {
            playBackgroundMusic(backgroundMusicBytes!!)
        }
    }

    override fun onStop(owner: LifecycleOwner) {
        stopBackgroundMusic()
    }
}

// Singleton provider remains the same
private val audioPlayerManagerInstance: AudioPlayerManager by lazy {
    AudioPlayerManager()
}

actual fun provideAudioPlayerManager(): AudioPlayerManager {
    return audioPlayerManagerInstance
}

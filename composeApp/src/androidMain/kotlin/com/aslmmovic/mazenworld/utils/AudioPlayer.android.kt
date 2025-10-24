package com.aslmmovic.mazenworld.utils

import android.content.Context
import android.media.MediaPlayer
import android.media.SoundPool
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


object AndroidContext : KoinComponent {
    val context: Context by inject()
    // Need a temporary, simple way to map resource names to Android IDs
    fun getResourceId(resourceId: String): Int {
        return context.resources.getIdentifier(resourceId, "raw", context.packageName)
    }
}

class AndroidAudioPlayer : AudioPlayer {

    private val soundPool: SoundPool = SoundPool.Builder().setMaxStreams(5).build()
    private val sfxMap = mutableMapOf<String, Int>()
    private var mediaPlayer: MediaPlayer? = null

    // NOTE: In a real app, you'd call loadSound() on init for all SFX

    override fun playSound(resourceId: String, volume: Float) {
        val resId = AndroidContext.getResourceId(resourceId)
        if (!sfxMap.containsKey(resourceId)) {
            sfxMap[resourceId] = soundPool.load(AndroidContext.context, resId, 1)
        }
        sfxMap[resourceId]?.let { soundId ->
            soundPool.play(soundId, volume, volume, 0, 0, 1f)
        }
    }

    override fun startMusic(resourceId: String, loop: Boolean) {
        // ... (MediaPlayer implementation for music) ...
    }
    override fun pauseMusic() { mediaPlayer?.pause() }
    override fun resumeMusic() { mediaPlayer?.start() }

    override fun stopAndRelease() {
        mediaPlayer?.release()
        soundPool.release()
    }
}

actual fun createAudioPlayer(): AudioPlayer = AndroidAudioPlayer()
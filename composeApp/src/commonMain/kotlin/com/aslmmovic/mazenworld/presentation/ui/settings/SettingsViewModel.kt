package com.aslmmovic.mazenworld.presentation.ui.settings


import androidx.lifecycle.ViewModel
import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager

class SettingsViewModel : ViewModel() {
    // Get the singleton instance of the AudioPlayerManager
    private val audioPlayer = provideAudioPlayerManager()

    // Expose the isMuted state directly from the AudioPlayerManager
    val isMuted = audioPlayer.isMuted

    // Expose the toggle function
    fun toggleMute() {
        audioPlayer.toggleMute()
    }
}

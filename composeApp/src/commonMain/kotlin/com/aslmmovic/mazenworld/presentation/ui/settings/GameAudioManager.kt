package com.aslmmovic.mazenworld.presentation.ui.settings


import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import mazenworld.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.ExperimentalResourceApi

const val animalsFolder = "files/animalsSrc"

@OptIn(ExperimentalResourceApi::class)
class GameAudioManager {

    private val player = provideAudioPlayerManager()

    fun playCorrectSound() = playSound("files/correct_chime.mp3")
    fun playIncorrectSound() = playSound("files/error_chime.mp3")
    fun playLevelCompleteSound() = playSound("files/kids_cheering.mp3")
    fun playQuestionVoice(voiceFileName: String?) {
        voiceFileName?.let { playSound("$animalsFolder/$it") }
    }


    fun playBackgroundMusic() = playBackground("files/background_music.ogg")


    fun playSpecificAnswerSound(soundFileName: String?) {
        soundFileName?.let { playSound("$animalsFolder/$it") }
    }

    private fun playSound(filePath: String) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                player.playSoundEffect(Res.readBytes(filePath))
            } catch (e: Exception) {
                // Log error
            }
        }
    }

    private fun playBackground(filePath: String) {
        CoroutineScope(Dispatchers.Default).launch {
            try {
                player.playBackgroundMusic(Res.readBytes(filePath))
            } catch (e: Exception) {
                // Log error
            }
        }
    }
}

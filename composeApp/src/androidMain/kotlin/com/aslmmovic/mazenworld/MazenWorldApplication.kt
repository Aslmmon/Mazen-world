package com.aslmmovic.mazenworld

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner
import com.aslmmovic.mazenworld.di.initKoin
import com.aslmmovic.mazenworld.utils.appContext
import com.aslmmovic.mazenworld.utils.provideAudioPlayerManager

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.android.ext.koin.androidContext

class MazenWorldApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = this.applicationContext
//        audioController = provideAudioController()

        val audioPlayerManager = provideAudioPlayerManager()
        ProcessLifecycleOwner.get().lifecycle.addObserver(audioPlayerManager)
        Firebase.initialize(this)
        initKoin {
            androidContext(this@MazenWorldApplication)
        }
    }
}

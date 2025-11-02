package com.aslmmovic.mazenworld

import android.app.Application
import com.aslmmovic.mazenworld.di.initKoin
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.initialize
import org.koin.android.ext.koin.androidContext

class MazenWorldApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        
        Firebase.initialize(this)
        initKoin {
            androidContext(this@MazenWorldApplication)
        }
    }
}

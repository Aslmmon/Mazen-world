package com.aslmmovic.mazenworld

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.initialize

class MazenWorldApp  : Application(){

    override fun onCreate() {
        super.onCreate()

        Firebase.initialize(this)

    }
}
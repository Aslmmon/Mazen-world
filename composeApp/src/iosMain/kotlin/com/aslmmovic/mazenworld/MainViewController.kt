package com.aslmmovic.mazenworld

import androidx.compose.ui.window.ComposeUIViewController
import com.aslmmovic.mazenworld.di.initKoin

fun MainViewController() = ComposeUIViewController(configure = {
    initKoin()
}) { App() }
package com.aslmmovic.mazenworld.presentation.components


import android.util.Log
import androidx.compose.runtime.Composable

@Composable
fun DebugLog(tag: String = "RecompositionCheck", message: String) {
    Log.d(tag, message)
}

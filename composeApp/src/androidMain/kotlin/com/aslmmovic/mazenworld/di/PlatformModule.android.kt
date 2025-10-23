package com.aslmmovic.mazenworld.di

import android.content.Context
import com.aslmmovic.mazenworld.data.source.AndroidPreferencesService
import com.aslmmovic.mazenworld.data.source.PreferencesService
import org.koin.core.module.Module
import org.koin.dsl.module

// 1. ACTUAL Function: Returns the Android-specific module containing the context-aware logic
//actual fun platformModule(): Module = module {
//    single<PreferencesService> {
//        createPlatformPreferencesService()
//    }
//}
//
//// 2. ACTUAL Function: Creates the AndroidPreferencesService using androidContext()
//actual fun createPlatformPreferencesService(private val androidContext: Context): PreferencesService {
//    return AndroidPreferencesService(androidContext)
//}


package com.aslmmovic.mazenworld.impl
// 1. EXPECTED Function: A function that will return the platform-specific Koin module
//expect fun platformModule(): Module
//
//// 2. EXPECTED Function: A function that will return the platform-specific PreferencesService instance
//expect fun createPlatformPreferencesService(): PreferencesService

interface Platform {
    val name: String
}



expect fun getPlatform(): Platform


package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.data.repository.MockGameRepository
import com.aslmmovic.mazenworld.data.repository.PersistentGameRepositoryImpl
import com.aslmmovic.mazenworld.data.source.UserLocalDataSource
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.presentation.ui.viewmodel.HomeViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// di/AppModule.kt
val appModule = module {
    // Single instance of the Mock repository for the entire app lifetime
    single<GameRepository> { MockGameRepository() }
    single { UserLocalDataSource(get()) }

    single<GameRepository> { PersistentGameRepositoryImpl(get()) }
    factory { HomeViewModel(get(), get(), get()) }
}

// di/KoinSetup.kt
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}
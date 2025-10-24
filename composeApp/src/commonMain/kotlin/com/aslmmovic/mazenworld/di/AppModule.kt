package com.aslmmovic.mazenworld.di

import ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.respository.MockGameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


// di/AppModule.kt
val appModule = module {

    single<GameRepository> { MockGameRepository() }

    factory {
        ToggleMusicEnabledUseCase(
            get(),
        )
    } // Inject GameRepository and PreferencesService
    factory {
        ToggleSoundEnabledUseCase(
            get(),
        )
    } // Inject GameRepository and PreferencesService

    factory { HomeViewModel(get(), get(), get()) }
    factory { SplashViewModel() }

//    factory { SplashViewModel(get()) }
}

// di/KoinSetup.kt
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}
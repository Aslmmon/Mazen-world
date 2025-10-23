package com.aslmmovic.mazenworld.di

import ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.data.repository.MockGameRepository
import com.aslmmovic.mazenworld.data.repository.PersistentGameRepositoryImpl
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

// di/AppModule.kt
val appModule = module {
    // Single instance of the Mock repository for the entire app lifetime
    single<GameRepository> { MockGameRepository() }

//
//    single<PreferencesService> {
//        // Here, we use a Koin factory function to create the correct platform implementation.
//        // You MUST define the actual implementation for Android and iOS separately.
//        // For Android: Inject the context to initialize DataStore/SharedPrefs.
//        // if (Platform.current == Platform.Android) {
//        AndroidPreferencesService(androidContext())
//        //  }
////        else {
////            IosPreferencesService()
////        }
//    }

    single<GameRepository> { PersistentGameRepositoryImpl(get()) }
    factory {
        ToggleMusicEnabledUseCase(
            get(),
            get()
        )
    } // Inject GameRepository and PreferencesService
    factory {
        ToggleSoundEnabledUseCase(
            get(),
            get()
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
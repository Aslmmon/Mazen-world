package com.aslmmovic.mazenworld.di

import ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.respository.MockGameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapViewModel
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GameViewModel
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
    }
    factory {
        ToggleSoundEnabledUseCase(
            get(),
        )
    }

    factory { HomeViewModel(get(), get(), get()) }


    factory { SplashViewModel() }
    factory { (categoryId: String) -> // <-- Accepts the runtime parameter
        GameViewModel(
            categoryId = categoryId, // Pass the runtime ID to the ViewModel's constructor
            gameRepository = get() // Injects GameRepository
        )
    }

    factory { CategoryMapViewModel() }
}

// di/KoinSetup.kt
fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(appModule)
}
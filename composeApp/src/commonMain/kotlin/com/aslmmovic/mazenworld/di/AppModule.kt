package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapViewModel
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GameViewModel
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import com.aslmmovic.mazenworld.presentation.ui.settings.GameAudioManager
import com.aslmmovic.mazenworld.presentation.ui.settings.SettingsViewModel
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashViewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


val homeModule = module {
    factory { HomeViewModel() }
}
val categoriesModule = module {
    factory { CategoryMapViewModel(get(), get()) }
}
val gameplayModule = module {
    factory { GameAudioManager() } // Add the new audio manager
    factory { (categoryId: String) ->
        GameViewModel(
            categoryId = categoryId,
            getQuestionsUseCase = get(),
            processAnswerUseCase = get(), // Inject the use cases
            gameAudioManager = get()
        )
    }
}
val settingsModule = module {
    single { SettingsViewModel() }
}

val splashModule = module {
    factory { SplashViewModel() }
}

private val allModules = listOf(
    dataModule,
    domainModule,
    homeModule,
    categoriesModule,
    gameplayModule,
    splashModule,
    settingsModule
)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(allModules)
}

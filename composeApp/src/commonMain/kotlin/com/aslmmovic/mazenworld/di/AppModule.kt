package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapViewModel
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GameViewModel
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
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
    factory { (categoryId: String) ->
        GameViewModel(
            categoryId = categoryId,
            getQuestionsUseCase = get(),
            publishQuestionsUseCase = get()
        )
    }
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
    splashModule
)

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(allModules)
}

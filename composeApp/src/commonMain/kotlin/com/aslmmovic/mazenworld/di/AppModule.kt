package com.aslmmovic.mazenworld.di

import ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.data.repositoryImpl.MapRepository
import com.aslmmovic.mazenworld.data.repositoryImpl.MockMapRepository
import com.aslmmovic.mazenworld.data.repositoryImplimport.CategoryRepositoryImpl
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.respository.MockGameRepository
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import com.aslmmovic.mazenworld.presentation.ui.categories.CategoryMapViewModel
import com.aslmmovic.mazenworld.presentation.ui.gameplay.GameViewModel
import com.aslmmovic.mazenworld.presentation.ui.home.HomeViewModel
import com.aslmmovic.mazenworld.presentation.ui.splash.SplashViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

val dataModule = module {
    single { Firebase.firestore }
    single<GameRepository> { MockGameRepository() }
    single<MapRepository> { MockMapRepository() }
    single<CategoryRepository> { CategoryRepositoryImpl() }
}

val domainModule = module {
    factory { GetCategoriesUseCase(get()) }
    factory { ToggleMusicEnabledUseCase(get()) }
    factory { ToggleSoundEnabledUseCase(get()) }
}

val homeModule = module {
    factory { HomeViewModel(get(), get(), get()) }
}
val categoriesModule = module {
    factory { CategoryMapViewModel(get()) }
}
val gameplayModule = module {
    factory { (categoryId: String) ->
        GameViewModel(
            categoryId = categoryId,
            gameRepository = get()
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

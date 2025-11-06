package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.domain.useCase.categories.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.categories.PublishCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.GetQuestionsUseCase
import com.aslmmovic.mazenworld.domain.useCase.game_play.PublishQuestionsUseCase
import org.koin.dsl.module

val domainModule = module {


    //Categories
    factory { PublishCategoriesUseCase(get()) }
    factory { GetCategoriesUseCase(get()) }

    //GamePlay
    factory { GetQuestionsUseCase(get()) }
    factory { PublishQuestionsUseCase(get()) }


}
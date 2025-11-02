package com.aslmmovic.mazenworld.di

import ToggleMusicEnabledUseCase
import com.aslmmovic.mazenworld.domain.useCase.GetCategoriesUseCase
import com.aslmmovic.mazenworld.domain.useCase.ToggleSoundEnabledUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCategoriesUseCase(get()) }
    factory { ToggleMusicEnabledUseCase(get()) }
    factory { ToggleSoundEnabledUseCase(get()) }
}
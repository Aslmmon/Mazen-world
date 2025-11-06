package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.data.repositoryImpl.CategoryRepositoryImpl
import com.aslmmovic.mazenworld.data.repositoryImpl.GamePlayRepositoryImpl
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository

import org.koin.dsl.module

val dataModule = module {
    single<CategoryRepository> { CategoryRepositoryImpl() }
    single<GamePlayRepository> { GamePlayRepositoryImpl() }

}
package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.data.repositoryImpl.MapRepository
import com.aslmmovic.mazenworld.data.repositoryImpl.MockMapRepository
import com.aslmmovic.mazenworld.data.repositoryImplimport.CategoryRepositoryImpl
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.respository.GameRepository
import com.aslmmovic.mazenworld.domain.respository.MockGameRepository
import org.koin.dsl.module

val dataModule = module {
    single<GameRepository> { MockGameRepository() }
    single<MapRepository> { MockMapRepository() }
    single<CategoryRepository> { CategoryRepositoryImpl() }
}
package com.aslmmovic.mazenworld.di

import com.aslmmovic.mazenworld.data.repositoryImpl.CategoryRepositoryImpl
import com.aslmmovic.mazenworld.data.repositoryImpl.GamePlayRepositoryImpl
import com.aslmmovic.mazenworld.domain.respository.CategoryRepository
import com.aslmmovic.mazenworld.domain.respository.GamePlayRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

import org.koin.dsl.module

val dataModule = module {
    single<SupabaseClient> {
        createSupabaseClient(
            supabaseUrl = "https://iwofjkeadvsodocbzfye.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Iml3b2Zqa2VhZHZzb2RvY2J6ZnllIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NjI0NTMwODMsImV4cCI6MjA3ODAyOTA4M30.CTlyDohfZE8K0Y0zcGvavRab295rfomR9-msUWiWQoI"
        ) {
            install(Postgrest)
        }
    }
    single<CategoryRepository> { CategoryRepositoryImpl() }
    single<GamePlayRepository> { GamePlayRepositoryImpl() }

}
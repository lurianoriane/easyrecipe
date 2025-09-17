package com.lurian.search.di

import com.lurian.search.data.remote.api.SearchApi
import com.lurian.search.data.remote.datasource.SearchRemoteDataSource
import com.lurian.search.data.remote.datasource.SearchRemoteDataSourceImpl
import com.lurian.search.data.repository.SearchRepositoryImpl
import com.lurian.search.domain.repository.SearchRepository
import com.lurian.search.domain.usecase.SearchRecipeUseCase
import com.lurian.search.presentation.viewmodel.SearchRecipeViewModel
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    single { HttpClient() }
    single { SearchApi(get()) }
    factory<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(get()) }
    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    factory { SearchRecipeUseCase(get()) }
    viewModel { SearchRecipeViewModel(get(), get()) }
}
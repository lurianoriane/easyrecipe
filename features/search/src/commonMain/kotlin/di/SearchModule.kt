package di

import data.remote.api.SearchApi
import data.remote.datasource.SearchRemoteDataSource
import data.remote.datasource.SearchRemoteDataSourceImpl
import data.repository.SearchRepositoryImpl
import domain.repository.SearchRepository
import domain.usecase.SearchRecipeUseCase
import io.ktor.client.HttpClient
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.viewmodel.SearchRecipeViewModel

val searchModule = module {
    single { HttpClient() }
    single { SearchApi(get()) }
    factory<SearchRemoteDataSource> { SearchRemoteDataSourceImpl(get()) }
    factory<SearchRepository> { SearchRepositoryImpl(get()) }
    factory { SearchRecipeUseCase(get()) }
    viewModel { SearchRecipeViewModel(get(), get()) }
}
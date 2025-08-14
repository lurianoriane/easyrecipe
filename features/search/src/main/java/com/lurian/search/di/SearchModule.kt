package com.lurian.search.di

import com.lurian.search.data.remote.api.SearchApi
import com.lurian.search.data.remote.datasource.SearchRemoteDataSource
import com.lurian.search.data.remote.datasource.SearchRemoteDataSourceImpl
import com.lurian.search.data.repository.SearchRepositoryImpl
import com.lurian.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.ktor.client.HttpClient

@InstallIn(ViewModelComponent::class)
@Module
class SearchModule {
    @Provides
    fun provideSearchApi(httpClient: HttpClient): SearchApi {
        return SearchApi(httpClient)
    }

    @Provides
    fun provideSearchDataSource(searchApi: SearchApi): SearchRemoteDataSource {
        return SearchRemoteDataSourceImpl(searchApi)
    }

    @Provides
    fun provideSearchRepository(dataSource: SearchRemoteDataSource): SearchRepository {
        return SearchRepositoryImpl(dataSource)
    }
}
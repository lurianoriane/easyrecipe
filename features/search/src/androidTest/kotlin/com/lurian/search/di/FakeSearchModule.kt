package com.lurian.search.di

import com.lurian.search.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import io.mockk.mockk
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [SearchModule::class]
)
object FakeSearchModule {

    @Singleton
    @Provides
    fun bindSearchRepository(): SearchRepository {
        return mockk<SearchRepository>()
    }
}

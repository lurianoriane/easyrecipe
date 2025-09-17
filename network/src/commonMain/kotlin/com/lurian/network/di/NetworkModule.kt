package com.lurian.network.di

import org.koin.dsl.module

// TODO: Remover este arquivo após atualizar todos os usos para Koin.
val networkModule = module {
    single<NetworkProvider> { NetworkProviderImpl(getKoin().getProperty("BASE_URL") ?: "") }
    single { get<NetworkProvider>().providesJson() }
    single { get<NetworkProvider>().providesHttpClientEngine() }
    single { get<NetworkProvider>().providesKtorClient(get(), get()) }
}

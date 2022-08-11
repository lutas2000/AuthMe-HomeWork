package com.lutas.authme.di

import com.lutas.authme.core.remote.ApiClient
import com.lutas.authme.core.remote.datasource.UserRemoteDataSource
import com.lutas.authme.core.remote.service.UserService
import org.koin.dsl.module

val remoteModule = module {
    single { ApiClient() }
    // Service
    single { createService<UserService>(get()) }
    // Data Source
    single { UserRemoteDataSource(get()) }
}

private inline fun <reified T> createService(client: ApiClient): T {
    return client.createService(T::class.java)
}
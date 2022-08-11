package com.lutas.authme.di

import com.lutas.authme.core.repository.UserRepository
import com.lutas.authme.domain.repository.IUserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { UserRepository(get()) as IUserRepository }
}
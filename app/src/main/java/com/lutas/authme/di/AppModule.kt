package com.lutas.authme.di

import com.lutas.authme.presentation.profile.viewmodel.UserProfileViewModel
import com.lutas.authme.presentation.users.viewmodel.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { UsersViewModel(get()) }
    viewModel { UserProfileViewModel(get()) }
}
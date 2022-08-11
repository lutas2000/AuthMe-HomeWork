package com.lutas.authme.domain.repository

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.lutas.authme.domain.model.Profile
import com.lutas.authme.domain.model.User
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    suspend fun getUsers(since: Int, perPage: Int): List<User>
    suspend fun getProfile(username: String): Profile
    fun getUsersPagingStream(): LiveData<PagingData<User>>
}
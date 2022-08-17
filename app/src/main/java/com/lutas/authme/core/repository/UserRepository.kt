package com.lutas.authme.core.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.lutas.authme.core.Config
import com.lutas.authme.core.remote.datasource.UserRemoteDataSource
import com.lutas.authme.domain.model.Profile
import com.lutas.authme.domain.model.User
import com.lutas.authme.domain.repository.IUserRepository
import com.lutas.authme.presentation.users.paging.UsersPagingSource

class UserRepository(private val userRemoteDataSource: UserRemoteDataSource) : IUserRepository {
    override suspend fun getUsers(since: Int, perPage: Int): List<User> =
        userRemoteDataSource.getUsers(since, perPage).map { it.mapToModel() }

    override suspend fun getProfile(username: String): Profile =
        userRemoteDataSource.getProfile(username).mapToModel()

    override fun getUsersPagingStream(): LiveData<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = Config.PER_PAGE,
                enablePlaceholders = false,
                initialLoadSize = 20
            ),
            pagingSourceFactory = { UsersPagingSource(userRemoteDataSource) }
        ).liveData
    }
}
package com.lutas.authme.core.remote.datasource

import com.lutas.authme.core.remote.entity.ProfileEntity
import com.lutas.authme.core.remote.entity.UserEntity
import com.lutas.authme.core.remote.service.UserService

class UserRemoteDataSource constructor(
    private val userService: UserService
) {
    suspend fun getUsers(since: Int, perPage: Int): List<UserEntity> =
        userService.getUsers(since, perPage)

    suspend fun getProfile(username: String): ProfileEntity =
        userService.getProfile(username)
}
package com.lutas.authme.core.remote.service

import com.lutas.authme.core.remote.entity.ProfileEntity
import com.lutas.authme.core.remote.entity.UserEntity
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UserService {
    @GET("/users")
    suspend fun getUsers(
        @Query("since") since: Int,
        @Query("per_page") perPage: Int
    ): List<UserEntity>

    @GET("users/{username}")
    suspend fun getProfile(
        @Path("username") username: String
    ): ProfileEntity
}
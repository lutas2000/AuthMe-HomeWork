package com.lutas.authme.core.remote.entity

import com.google.gson.annotations.SerializedName
import com.lutas.authme.domain.model.User

data class UserEntity(
    @SerializedName(value = "avatar_url")
    val avatarUrl: String?,
    val id: String?,
    val login: String?,
    @SerializedName(value = "site_admin")
    val siteAdmin: Boolean?
) {
    fun mapToModel(): User = User(
        avatarUrl = avatarUrl,
        id = id,
        login = login ?: "",
        siteAdmin = siteAdmin ?: false
    )
}
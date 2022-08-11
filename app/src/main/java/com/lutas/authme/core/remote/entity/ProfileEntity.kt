package com.lutas.authme.core.remote.entity

import com.google.gson.annotations.SerializedName
import com.lutas.authme.domain.model.Profile

data class ProfileEntity(
    @SerializedName(value = "avatar_url")
    val avatarUrl: String?,
    val name: String?,
    val bio: String?,
    val login: String?,
    @SerializedName(value = "site_admin")
    val siteAdmin: Boolean?,
    val location: String?,
    val blog: String?
) {
    fun mapToModel(): Profile = Profile(
        avatarUrl = avatarUrl,
        name = name,
        bio = bio,
        login = login ?: "",
        siteAdmin = siteAdmin ?: false,
        location = location,
        blog = blog
    )
}


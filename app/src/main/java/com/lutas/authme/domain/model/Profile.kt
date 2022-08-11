package com.lutas.authme.domain.model

data class Profile(
    val avatarUrl: String?,
    val name: String?,
    val bio: String?,
    val login: String,
    val siteAdmin: Boolean,
    val location: String?,
    val blog: String?
)
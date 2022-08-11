package com.lutas.authme.domain.model

data class User(
    val avatarUrl: String?,
    val id: String?,
    val login: String,
    val siteAdmin: Boolean
)
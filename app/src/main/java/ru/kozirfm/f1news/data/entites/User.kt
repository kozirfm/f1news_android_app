package ru.kozirfm.f1news.data.entites

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val username: String,
    val email: String,
    val password: String
)
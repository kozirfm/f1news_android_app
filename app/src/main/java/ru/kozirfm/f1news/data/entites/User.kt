package ru.kozirfm.f1news.data.entites

import com.google.gson.annotations.Expose

data class User(
    @Expose
    val username: String,
    @Expose
    val password: String,
    @Expose
    val email: String
)
package ru.kozirfm.f1news.data.entites

import com.google.gson.annotations.Expose

data class Team(
    @Expose
    val drivers: List<Driver>,
    @Expose
    val name: String,
    var points: Int
)
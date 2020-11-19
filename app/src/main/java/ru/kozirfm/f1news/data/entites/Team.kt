package ru.kozirfm.f1news.data.entites

import com.google.gson.annotations.Expose

data class Team(
    @Expose
    val drivers: List<Driver>,
    @Expose
    val name: String,
    @Expose
    var points: Int
)
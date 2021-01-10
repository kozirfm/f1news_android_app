package ru.kozirfm.f1news.data.entites

import kotlinx.serialization.Serializable

@Serializable
data class Team(
    val drivers: List<Driver>,
    val name: String,
    var points: Int,
)
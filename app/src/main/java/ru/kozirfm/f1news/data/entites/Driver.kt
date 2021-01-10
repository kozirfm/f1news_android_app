package ru.kozirfm.f1news.data.entites

import kotlinx.serialization.Serializable

@Serializable
data class Driver(
    val position: Int,
    val name: String,
    val surname: String,
    val team: String,
    val points: Int,
)
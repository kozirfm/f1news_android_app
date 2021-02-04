package ru.kozirfm.f1news.data.entites

import kotlinx.serialization.Serializable

@Serializable
data class GrandPrix(
    val date: String,
    val flag: String,
    val grandPrix: String,
    val track: String,
    val length: String,
    val laps: String,
    val distance: String,
    val driver: String,
    val team: String,
)
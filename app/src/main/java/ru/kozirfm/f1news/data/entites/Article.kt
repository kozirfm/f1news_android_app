package ru.kozirfm.f1news.data.entites

import kotlinx.serialization.Serializable

@Serializable
data class Article(
    val id: Long,
    val date: String,
    val title: String,
    val link: String,
    val text: String,
    val images: List<String>? = null
)
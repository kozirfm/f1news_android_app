package ru.kozirfm.f1news.data.entites

sealed class News {
    data class NewsSimple(
        val id: Long,
        val date: String,
        val title: String,
        val link: String,
        val text: String
    ) : News()

    data class NewsWithImage(
        val id: Long,
        val date: String,
        val title: String,
        val link: String,
        val text: String,
        val images: List<String>
    ) : News()
}
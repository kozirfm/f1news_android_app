package ru.kozirfm.f1news.data.entites

import com.google.gson.annotations.Expose

data class Article(
    @Expose
    val id: Long,
    @Expose
    val date: String,
    @Expose
    val title: String,
    @Expose
    val link: String,
    @Expose
    val text: String)
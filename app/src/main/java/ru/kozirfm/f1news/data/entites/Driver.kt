package ru.kozirfm.f1news.data.entites

import com.google.gson.annotations.Expose

data class Driver(
    @Expose
    val position: Int,
    @Expose
    val name: String,
    @Expose
    val surname: String,
    @Expose
    val team: String,
    @Expose
    val points: Int)
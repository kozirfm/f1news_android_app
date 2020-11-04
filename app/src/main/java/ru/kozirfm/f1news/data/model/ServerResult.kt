package ru.kozirfm.f1news.data.model

sealed class ServerResult {
    data class Success<T>(val data: T) : ServerResult()
    data class Error(val t: Throwable?) : ServerResult()
}
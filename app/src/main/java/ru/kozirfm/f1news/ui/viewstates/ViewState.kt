package ru.kozirfm.f1news.ui.viewstates

sealed class ViewState
object Loading : ViewState()
data class Data<T>(val data: T) : ViewState()
data class Error(val t: Throwable?) : ViewState()

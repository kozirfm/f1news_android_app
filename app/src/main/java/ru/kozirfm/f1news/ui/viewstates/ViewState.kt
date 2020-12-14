package ru.kozirfm.f1news.ui.viewstates

sealed class ViewState
data class Data<T>(val data: T) : ViewState()
data class Error(val t: Throwable?) : ViewState()
object Loading : ViewState()

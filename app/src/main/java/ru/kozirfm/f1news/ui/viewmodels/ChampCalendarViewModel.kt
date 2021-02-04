package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import ru.kozirfm.f1news.data.datasource.RemoteDataSource
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading
import ru.kozirfm.f1news.ui.viewstates.ViewState

class ChampCalendarViewModel(
    val remoteDataSource: RemoteDataSource,
    val viewState: MutableLiveData<ViewState>,
) :
    ViewModel() {

    fun getData(): LiveData<ViewState> {
        viewState.value = Loading
        viewModelScope.launch(CoroutineExceptionHandler { _, throwable ->
            viewState.value = Error(throwable)
        }) {

            viewState.value = Data(remoteDataSource.getCalendar())
        }
        return viewState
    }
}
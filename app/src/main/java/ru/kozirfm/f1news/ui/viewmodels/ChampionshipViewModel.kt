package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading
import ru.kozirfm.f1news.ui.viewstates.ViewState

class ChampionshipViewModel : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        viewState.value = Error(throwable)
    }

    fun getData(): LiveData<ViewState> {
        viewState.value = Loading
        viewModelScope.launch(exceptionHandler) {
            viewState.value = Data(Repository.getTeams())
        }
        return viewState
    }

    override fun onCleared() {
        exceptionHandler.cancel()
        super.onCleared()
    }

}

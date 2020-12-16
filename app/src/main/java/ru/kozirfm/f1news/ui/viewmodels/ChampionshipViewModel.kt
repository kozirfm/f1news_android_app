package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.ViewState

class ChampionshipViewModel : ViewModel() {

    private val viewState = MutableLiveData<ViewState>()

    fun getData(): LiveData<ViewState> {
        viewModelScope.launch {
            viewState.value = Data(Repository.getTeams())
        }
        return viewState
    }

    override fun onCleared() {
        viewModelScope.cancel()
        super.onCleared()
    }
}

package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.model.Error
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.model.Success
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.*

class ChampionshipViewModel : ViewModel() {

    val viewState = MutableLiveData<ViewState>()
    private val repositoryChampionship = Repository.getTeams()

    private val championshipObserver = Observer<ServerResult> { result ->
        result ?: return@Observer
        when (result) {
            is Success<*> -> {
                viewState.value = Data(result.data)
            }
            is Error -> viewState.value = ru.kozirfm.f1news.ui.viewstates.Error(result.t)
        }
    }

    init {
        repositoryChampionship.observeForever(championshipObserver)
    }

    override fun onCleared() {
        super.onCleared()
        repositoryChampionship.removeObserver(championshipObserver)
    }
}

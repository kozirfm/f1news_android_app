package ru.kozirfm.f1news.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.data.model.ServerResult
import ru.kozirfm.f1news.data.model.ServerResult.Error
import ru.kozirfm.f1news.data.model.ServerResult.Success
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewstates.ChampionshipViewState

@Suppress("UNCHECKED_CAST")
class ChampionshipViewModel : ViewModel() {

    val viewState = MutableLiveData<ChampionshipViewState>()
    private val repositoryChampionship = Repository.getTeams()

    private val championshipObserver = Observer<ServerResult> { result ->
        result ?: return@Observer
        when (result) {
            is Success<*> -> {
                val teams = result.data as List<Team>
                val drivers = ArrayList<Driver>()
                teams.forEach { team ->
                    drivers.addAll(team.drivers)
                }
                teams.sortedByDescending { team -> team.points }
                drivers.sortBy { driver -> driver.position }
                viewState.value = ChampionshipViewState.ShowDrivers(drivers)
                viewState.value = ChampionshipViewState.ShowTeams(teams)
            }
            is Error -> viewState.value = ChampionshipViewState.ShowError(result.t)
        }
    }

    init {
        repositoryChampionship.observeForever(championshipObserver)
    }

    override fun onCleared() {
        repositoryChampionship.removeObserver(championshipObserver)
        super.onCleared()
    }
}

package ru.kozirfm.f1news.ui.viewstates

import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.data.entites.Team

sealed class ChampionshipViewState {
    data class ShowDrivers(val drivers: List<Driver>?) : ChampionshipViewState()
    data class ShowTeams(val teams: List<Team>?) : ChampionshipViewState()
    data class ShowError(val t: Throwable?) : ChampionshipViewState()
}
package ru.kozirfm.f1news.ui.viewstates

import ru.kozirfm.f1news.data.entites.Driver

sealed class ChampionshipViewState {
    data class ShowDrivers(val drivers: List<Driver>?) : ChampionshipViewState()
    data class ShowError(val t: Throwable?) : ChampionshipViewState()
}
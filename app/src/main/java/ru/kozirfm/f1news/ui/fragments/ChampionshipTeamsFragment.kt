package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_teams.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipTeamsRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
class ChampionshipTeamsFragment :
    BaseFragment(R.layout.fragment_championship_teams) {

    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val championshipTeamsRecyclerViewAdapter by lazy { ChampionshipTeamsRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipTeamsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipTeamsRecyclerView.adapter = championshipTeamsRecyclerViewAdapter
        championshipTeamsRecyclerView.setHasFixedSize(true)
        arguments?.getString("Team")?.let { Json.decodeFromString<List<Team>>(string = it) }
            ?.let { championshipTeamsRecyclerViewAdapter.teamsTable = it}
    }

    override fun onDestroy() {
        println("TeamsFragmentOnDestroy")
        super.onDestroy()
    }

}

package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.databinding.FragmentChampionshipTeamsBinding
import ru.kozirfm.f1news.ui.adapters.ChampionshipTeamsRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
class ChampionshipTeamsFragment :
    BaseFragment(R.layout.fragment_championship_teams) {

    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val championshipTeamsRecyclerViewAdapter by lazy { ChampionshipTeamsRecyclerViewAdapter() }

    lateinit var fragmentChampionshipTeamsBinding: FragmentChampionshipTeamsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentChampionshipTeamsBinding = FragmentChampionshipTeamsBinding.bind(view)
        fragmentChampionshipTeamsBinding.championshipTeamsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        fragmentChampionshipTeamsBinding.championshipTeamsRecyclerView.adapter = championshipTeamsRecyclerViewAdapter
        fragmentChampionshipTeamsBinding.championshipTeamsRecyclerView.setHasFixedSize(true)
        arguments?.getString("Team")?.let { Json.decodeFromString<List<Team>>(string = it) }
            ?.let { championshipTeamsRecyclerViewAdapter.teamsTable = it}
    }

    override fun onDestroy() {
        println("TeamsFragmentOnDestroy")
        super.onDestroy()
    }

}

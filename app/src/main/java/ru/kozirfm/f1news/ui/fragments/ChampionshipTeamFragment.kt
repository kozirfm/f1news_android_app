package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_team.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.adapters.ChampionshipTeamsRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.ChampionshipViewState

class ChampionshipTeamFragment(private val championshipViewModel: ChampionshipViewModel) :
    Fragment() {

    private val championshipTeamsRecyclerViewAdapter by lazy { ChampionshipTeamsRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_championship_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipTeamRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipTeamRecyclerView.adapter = championshipTeamsRecyclerViewAdapter
        championshipTeamRecyclerView.setHasFixedSize(true)

        championshipViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is ChampionshipViewState.ShowTeams -> viewState.teams?.let {
                    championshipTeamsRecyclerViewAdapter.teamsTable = it
                }
                is ChampionshipViewState.ShowError -> Toast.makeText(
                    requireContext(),
                    "${viewState.t}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

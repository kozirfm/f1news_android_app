package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_teams.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipTeamsRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error

@Suppress("UNCHECKED_CAST")
class ChampionshipTeamsFragment(private val championshipViewModel: ChampionshipViewModel) :
    BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship_teams
    override val recyclerView: Int = R.id.championshipTeamsRecyclerView
    override val progressBar: Int = R.id.championshipTeamsProgressBar

    private val championshipTeamsRecyclerViewAdapter by lazy { ChampionshipTeamsRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipTeamsRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipTeamsRecyclerView.adapter = championshipTeamsRecyclerViewAdapter
        championshipTeamsRecyclerView.setHasFixedSize(true)

        championshipViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Data<*> -> {
                    viewState.data?.let {
                        championshipTeamsRecyclerViewAdapter.teamsTable = it as List<Team>
                    }
                    stopLoading()
                }
                is Error -> Toast.makeText(
                    requireContext(),
                    "${viewState.t}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

}

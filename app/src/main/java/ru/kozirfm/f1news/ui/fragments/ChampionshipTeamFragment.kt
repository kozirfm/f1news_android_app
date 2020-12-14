package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_team.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipTeamsRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading

@Suppress("UNCHECKED_CAST")
class ChampionshipTeamFragment(private val championshipViewModel: ChampionshipViewModel) :
    BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship_team

    private val championshipTeamsRecyclerViewAdapter by lazy { ChampionshipTeamsRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipTeamRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipTeamRecyclerView.adapter = championshipTeamsRecyclerViewAdapter
        championshipTeamRecyclerView.setHasFixedSize(true)

        championshipViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> TODO()
                is Data<*> -> viewState.data?.let {
                    championshipTeamsRecyclerViewAdapter.teamsTable = it as List<Team>
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

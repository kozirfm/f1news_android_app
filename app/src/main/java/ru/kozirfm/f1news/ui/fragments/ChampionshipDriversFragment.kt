package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_drivers.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipDriversRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error

@Suppress("UNCHECKED_CAST")
class ChampionshipDriversFragment(private val championshipViewModel: ChampionshipViewModel) :
    BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship_drivers
    override val recyclerView: Int = R.id.championshipDriversRecyclerView
    override val progressBar: Int = R.id.championshipDriversProgressBar

    private val championshipRecyclerViewAdapter by lazy { ChampionshipDriversRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipDriversRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipDriversRecyclerView.adapter = championshipRecyclerViewAdapter
        championshipDriversRecyclerView.setHasFixedSize(true)

        championshipViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Data<*> -> {
                    viewState.data?.let {
                        val drivers = ArrayList<Driver>()
                        (it as List<Team>).forEach { team ->
                            drivers.addAll(team.drivers)
                        }
                        drivers.sortBy { driver -> driver.position }
                        championshipRecyclerViewAdapter.driversTable = drivers
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

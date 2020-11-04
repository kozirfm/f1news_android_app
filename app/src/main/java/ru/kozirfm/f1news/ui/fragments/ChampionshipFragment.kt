package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.adapters.ChampionshipRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.ChampionshipViewState

class ChampionshipFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship
    override val fragmentTag: String = ChampionshipFragment::class.java.name + "TAG"

    private val championshipViewModel by lazy { ViewModelProvider(this).get(ChampionshipViewModel::class.java) }
    private val championshipRecyclerViewAdapter by lazy { ChampionshipRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipRecyclerView.adapter = championshipRecyclerViewAdapter
        championshipViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is ChampionshipViewState.ShowDrivers -> viewState.drivers?.let {
                    championshipRecyclerViewAdapter.championshipTable = it
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

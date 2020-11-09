package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_drivers.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.adapters.ChampionshipDriversRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.ChampionshipViewState

class ChampionshipDriversFragment(private val championshipViewModel: ChampionshipViewModel) :
    Fragment() {

    private val championshipRecyclerViewAdapter by lazy { ChampionshipDriversRecyclerViewAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_championship_drivers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        championshipDriversRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipDriversRecyclerView.adapter = championshipRecyclerViewAdapter
        championshipDriversRecyclerView.setHasFixedSize(true)

        championshipViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is ChampionshipViewState.ShowDrivers -> viewState.drivers?.let {
                    championshipRecyclerViewAdapter.driversTable = it
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

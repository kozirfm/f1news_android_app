package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_championship_drivers.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.ui.adapters.ChampionshipDriversRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
class ChampionshipDriversFragment :
    BaseFragment(R.layout.fragment_championship_drivers) {

    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val championshipRecyclerViewAdapter by lazy { ChampionshipDriversRecyclerViewAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        arguments?.getString("Driver")?.let { Json.decodeFromString<List<Driver>>(string = it) }
            ?.let { championshipRecyclerViewAdapter.driversTable = it }
    }

    private fun initRecyclerView() {
        championshipDriversRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        championshipDriversRecyclerView.adapter = championshipRecyclerViewAdapter
        championshipDriversRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroy() {
        println("DriversFragmentOnDestroy")
        super.onDestroy()
    }
}

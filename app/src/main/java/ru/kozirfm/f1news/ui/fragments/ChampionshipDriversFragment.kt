package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.databinding.FragmentChampionshipDriversBinding
import ru.kozirfm.f1news.ui.adapters.ChampionshipDriversRecyclerViewAdapter

@Suppress("UNCHECKED_CAST")
class ChampionshipDriversFragment :
    BaseFragment(R.layout.fragment_championship_drivers) {

    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val championshipRecyclerViewAdapter by lazy { ChampionshipDriversRecyclerViewAdapter() }
    lateinit var fragmentChampionshipDriversBinding: FragmentChampionshipDriversBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentChampionshipDriversBinding = FragmentChampionshipDriversBinding.bind(view)
        initRecyclerView()
        arguments?.getString("Driver")?.let { Json.decodeFromString<List<Driver>>(string = it) }
            ?.let { championshipRecyclerViewAdapter.driversTable = it }
    }

    private fun initRecyclerView() {
        fragmentChampionshipDriversBinding.championshipDriversRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        fragmentChampionshipDriversBinding.championshipDriversRecyclerView.adapter = championshipRecyclerViewAdapter
        fragmentChampionshipDriversBinding.championshipDriversRecyclerView.setHasFixedSize(true)
    }

    override fun onDestroy() {
        println("DriversFragmentOnDestroy")
        super.onDestroy()
    }
}

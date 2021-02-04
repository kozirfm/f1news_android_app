package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.GrandPrix
import ru.kozirfm.f1news.databinding.FragmentChampCalendarBinding
import ru.kozirfm.f1news.ui.adapters.ChampCalendarRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampCalendarViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading

@Suppress("UNCHECKED_CAST")
class ChampCalendarFragment : BaseFragment(R.layout.fragment_champ_calendar) {
    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val champCalendarViewModel by viewModel<ChampCalendarViewModel>()
    private val champCalendarRecyclerViewAdapter by lazy { ChampCalendarRecyclerViewAdapter() }
    lateinit var fragmentChampCalendarBinding: FragmentChampCalendarBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentChampCalendarBinding = FragmentChampCalendarBinding.bind(view)

        fragmentChampCalendarBinding
            .calendarRecyclerView
            .layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        fragmentChampCalendarBinding
            .calendarRecyclerView
            .adapter = champCalendarRecyclerViewAdapter

        champCalendarViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> startLoading(fragmentChampCalendarBinding.calendarRecyclerView,
                    fragmentChampCalendarBinding.calendarProgressBar)
                is Data<*> -> {
                    champCalendarRecyclerViewAdapter.calendar = viewState.data as List<GrandPrix>
                    stopLoading(fragmentChampCalendarBinding.calendarRecyclerView,
                        fragmentChampCalendarBinding.calendarProgressBar)
                }
                is Error -> println("Error")
            }
        }
    }

}
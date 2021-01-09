package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_championship.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipViewPagerAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading

@Suppress("UNCHECKED_CAST")
class ChampionshipFragment : BaseFragment(R.layout.fragment_championship) {

    override val bottomNavigationVisibility: Int = View.VISIBLE

    private val championshipViewModel by viewModels<ChampionshipViewModel>()
    private val championshipTeamsFragment by lazy { ChampionshipTeamsFragment() }
    private val championshipDriversFragment by lazy { ChampionshipDriversFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        championshipViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> startLoading(goneView = championshipViewPager,
                    visibleView = championshipProgressBar)
                is Data<*> -> {
                    viewState.data?.let {
                        championshipTeamsFragment.arguments = Bundle()
                        championshipTeamsFragment.arguments?.putParcelableArrayList(
                            "Team",
                            ArrayList(it as List<Team>)
                        )
                        championshipDriversFragment.arguments = Bundle()
                        championshipDriversFragment.arguments?.putParcelableArrayList(
                            "Driver",
                            ArrayList((it as List<Team>).flatMap { team -> team.drivers }
                                .sortedBy { driver -> driver.position })
                        )
                    }
                    stopLoading(
                        visibleView = championshipViewPager,
                        goneView = championshipProgressBar
                    )
                }
                is Error -> Toast.makeText(
                    requireContext(),
                    "${viewState.t}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val championshipViewPagerAdapter = ChampionshipViewPagerAdapter(this)
        championshipViewPager.adapter = championshipViewPagerAdapter
        championshipViewPagerAdapter.pages =
            listOf(championshipDriversFragment, championshipTeamsFragment)

        TabLayoutMediator(
            changeChampionshipTableTabLayout,
            championshipViewPager
        ) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> tab.text = "Личный зачёт"
                1 -> tab.text = "Командный зачёт"
            }
        }.attach()
    }

}

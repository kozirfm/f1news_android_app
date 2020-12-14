package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_championship.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.Driver
import ru.kozirfm.f1news.data.entites.Team
import ru.kozirfm.f1news.ui.adapters.ChampionshipViewPagerAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error
import ru.kozirfm.f1news.ui.viewstates.Loading

@Suppress("UNCHECKED_CAST")
class ChampionshipFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship

    private val championshipViewModel by viewModels<ChampionshipViewModel>()
    private var data: List<Driver>? = null
    private val championshipTeamFragment by lazy { ChampionshipTeamFragment(championshipViewModel) }
    private val championshipDriversFragment by lazy {
        ChampionshipDriversFragment(championshipViewModel)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        championshipViewModel.viewState.observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Loading -> TODO()
                is Data<*> -> viewState.data?.let {
                    val drivers = ArrayList<Driver>()
                    (it as List<Team>).forEach { team ->
                        drivers.addAll(team.drivers)
                    }
                    drivers.sortBy { driver -> driver.position }
                    data = drivers
                }
                is Error -> Toast.makeText(
                    requireContext(),
                    "${viewState.t}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        val championshipViewPagerAdapter = ChampionshipViewPagerAdapter(this)
        viewPager.isSaveEnabled = false
        viewPager.adapter = championshipViewPagerAdapter
        championshipViewPagerAdapter.pages =
            listOf(championshipDriversFragment, championshipTeamFragment)

        TabLayoutMediator(
            changeChampionshipTableTabLayout,
            viewPager
        ) { tab: TabLayout.Tab, i: Int ->
            when (i) {
                0 -> {
                    tab.text = "Личный зачёт"
                    tab.icon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_outline_sports_motorsports_24
                    )
                }
                1 -> {
                    tab.text = "Командный зачёт"
                    tab.icon = ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_supervisor_account_24
                    )
                }
            }
        }.attach()
    }
}

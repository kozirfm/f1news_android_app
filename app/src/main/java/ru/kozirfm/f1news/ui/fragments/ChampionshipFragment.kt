package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_championship.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.adapters.ChampionshipViewPagerAdapter
import ru.kozirfm.f1news.ui.viewmodels.ChampionshipViewModel

class ChampionshipFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_championship

    private val championshipViewModel by lazy {
        ViewModelProvider(this).get(
            ChampionshipViewModel::class.java
        )
    }

    private val championshipTeamFragment by lazy { ChampionshipTeamFragment(championshipViewModel) }
    private val championshipDriversFragment by lazy {
        ChampionshipDriversFragment(
            championshipViewModel
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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

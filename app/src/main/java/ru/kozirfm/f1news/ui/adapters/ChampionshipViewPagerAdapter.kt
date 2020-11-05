package ru.kozirfm.f1news.ui.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import ru.kozirfm.f1news.ui.fragments.ChampionshipDriversFragment
import ru.kozirfm.f1news.ui.fragments.ChampionshipTeamFragment

class ChampionshipViewPagerAdapter(fragment: Fragment) :
    FragmentStateAdapter(fragment) {

    private val pages: List<Fragment> = listOf(ChampionshipDriversFragment(), ChampionshipTeamFragment())

    override fun createFragment(position: Int): Fragment {
        return pages[position]
    }

    override fun getItemCount(): Int {
        return pages.count()
    }

}

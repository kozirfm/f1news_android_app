package ru.kozirfm.f1news.ui.activity

import ru.kozirfm.f1news.ui.fragments.BaseFragment

object FragmentManager {

    var fragmentManagerChanger: MainActivity? = null

    fun replaceFragment(fragment: BaseFragment) =
        fragmentManagerChanger?.replaceFragment(fragment)

    fun finishFragment() = fragmentManagerChanger?.finishFragment()

}
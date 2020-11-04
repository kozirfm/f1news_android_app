package ru.kozirfm.f1news.ui.activity

import ru.kozirfm.f1news.ui.fragments.BaseFragment

interface FragmentManagerChanger {

    fun replaceFragment(fragment: BaseFragment)
    fun finishFragment()

}
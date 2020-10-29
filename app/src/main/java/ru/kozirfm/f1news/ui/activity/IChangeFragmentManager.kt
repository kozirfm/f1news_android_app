package ru.kozirfm.f1news.ui.activity

import androidx.fragment.app.Fragment

interface IChangeFragmentManager {

    fun replaceFragment(fragment: Fragment, tag: String?)
    fun finishFragment()

}
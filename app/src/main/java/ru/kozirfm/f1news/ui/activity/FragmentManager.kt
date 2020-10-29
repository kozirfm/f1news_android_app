package ru.kozirfm.f1news.ui.activity

import androidx.fragment.app.Fragment

object FragmentManager {

    var changeFragmentManager: MainActivity? = null

    fun replaceFragment(fragment: Fragment, tag: String? = null) =
        changeFragmentManager?.replaceFragment(fragment, tag)

    fun finishFragment() = changeFragmentManager?.finishFragment()

}
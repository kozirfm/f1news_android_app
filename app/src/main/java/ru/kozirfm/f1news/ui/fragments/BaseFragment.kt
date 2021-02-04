package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.kozirfm.f1news.R

abstract class BaseFragment(fragmentLayout: Int) : Fragment(fragmentLayout) {

    abstract val bottomNavigationVisibility: Int

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavigationView)
            .visibility = bottomNavigationVisibility
    }

    fun startLoading(goneView: View, visibleView: View) {
        goneView.visibility = View.GONE
        visibleView.visibility = View.VISIBLE
    }

    fun stopLoading(visibleView: View, goneView: View) {
        visibleView.visibility = View.VISIBLE
        goneView.visibility = View.GONE
    }
}

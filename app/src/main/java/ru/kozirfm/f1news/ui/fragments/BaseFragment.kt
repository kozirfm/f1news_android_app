package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

abstract class BaseFragment : Fragment() {

    abstract val bottomNavigationVisibility: Int
    abstract val fragmentLayout: Int
    abstract val recyclerView: Int?
    abstract val progressBar: Int?

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(fragmentLayout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().bottomNavigationView.visibility = bottomNavigationVisibility
        if (recyclerView != null && progressBar != null) startLoading()
    }

    fun startLoading() {
        progressBar?.let { view?.findViewById<ProgressBar>(it)?.visibility = View.VISIBLE }
        recyclerView?.let { view?.findViewById<RecyclerView>(it)?.visibility = View.INVISIBLE }
    }

    fun stopLoading() {
        recyclerView?.let { view?.findViewById<RecyclerView>(it)?.visibility = View.VISIBLE }
        progressBar?.let { view?.findViewById<ProgressBar>(it)?.visibility = View.INVISIBLE }
    }
}

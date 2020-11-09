package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.activity.FragmentManager
import ru.kozirfm.f1news.ui.adapters.NewsRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.NewsViewState

class NewsFragment : BaseFragment() {

    private val newsViewModel: NewsViewModel by lazy { ViewModelProvider(this).get(NewsViewModel::class.java) }

    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_news
    override val fragmentTag: String = NewsFragment::class.java.name + "TAG"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val articlesRecyclerViewAdapter = NewsRecyclerViewAdapter { text ->
            val args = Bundle()
            args.putString(NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS, text)
            val bottomSheetDialogFragment = NewsTextBottomSheetDialogFragment()
            bottomSheetDialogFragment.arguments = args
            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, tag
            )
        }

        mainActivityToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.signInMenuItem -> {
                    FragmentManager.replaceFragment(
                        fragment = AuthorizationFragment()
                    )
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }

        articlesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        articlesRecyclerView.adapter = articlesRecyclerViewAdapter
        articlesRecyclerView.hasFixedSize()

        newsViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is NewsViewState.ShowArticles -> viewState.articles?.let {
                    articlesRecyclerViewAdapter.articles = it
                }
                is NewsViewState.ShowError -> {
                    val errorToast: Toast = Toast.makeText(
                        requireContext(),
                        getString(R.string.server_error),
                        Toast.LENGTH_SHORT
                    )
                    errorToast.setGravity(Gravity.CENTER, 0, 0)
                    errorToast.show()
                }
            }
        }
    }
}

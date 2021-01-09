package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState.Loading
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.*
import ru.kozirfm.f1news.ui.adapters.NewsAdapter
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.Data
import ru.kozirfm.f1news.ui.viewstates.Error

@Suppress("UNCHECKED_CAST")
class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val newsViewModel by viewModels<NewsViewModel>()
    override val bottomNavigationVisibility: Int = View.VISIBLE
    private val bottomSheetDialogFragment by lazy { NewsTextBottomSheetDialogFragment() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bottomSheetDialogFragment.arguments = Bundle()
        val newsRecyclerViewAdapter = NewsAdapter { news ->
            when (news) {
                is NewsSimple -> bottomSheetDialogFragment.arguments?.putString(
                    NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS,
                    news.text
                )
                is NewsWithImage -> bottomSheetDialogFragment.arguments?.putString(
                    NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS,
                    news.text
                )
            }
            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, tag
            )
        }

        mainActivityToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.signInMenuItem -> {
                    findNavController().navigate(R.id.action_nav_news_to_authorizationFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        articlesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        articlesRecyclerView.setHasFixedSize(true)
        articlesRecyclerView.adapter = newsRecyclerViewAdapter
        newsRecyclerViewAdapter.addLoadStateListener { states ->
            if (states.refresh == Loading) {
                startLoading(articlesRecyclerView, articlesProgressBar)
            }
            if (states.prepend.endOfPaginationReached) {
                stopLoading(articlesRecyclerView, articlesProgressBar)
            }
        }

        newsViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Data<*> -> {
                    viewState.data?.let {
                        newsRecyclerViewAdapter.submitData(
                            viewLifecycleOwner.lifecycle,
                            it as PagingData<News>
                        )
                    }
                }
                is Error -> {}
            }
        }
    }
}
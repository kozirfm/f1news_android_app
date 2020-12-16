package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.*
import ru.kozirfm.f1news.ui.adapters.NewsAdapter
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.Data

@Suppress("UNCHECKED_CAST")
class NewsFragment : BaseFragment() {

    private val newsViewModel by viewModels<NewsViewModel>()
    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_news
    override val recyclerView: Int = R.id.articlesRecyclerView
    override val progressBar: Int = R.id.articlesProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = Bundle()
        val adapter = NewsAdapter({
            stopLoading()
        },
            { news ->
                when (news) {
                    is NewsSimple -> args.putString(
                        NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS,
                        news.text
                    )
                    is NewsWithImage -> args.putString(
                        NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS,
                        news.text
                    )
                }
                val bottomSheetDialogFragment = NewsTextBottomSheetDialogFragment()
                bottomSheetDialogFragment.arguments = args
                bottomSheetDialogFragment.show(
                    requireActivity().supportFragmentManager, tag
                )
            })

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
        articlesRecyclerView.adapter = adapter

        newsViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Data<*> -> {
                    viewState.data?.let {
                        adapter.submitData(viewLifecycleOwner.lifecycle, it as PagingData<News>)
                    }
                }
            }
        }
    }
}
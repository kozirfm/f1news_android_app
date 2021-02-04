package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState.Loading
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.*
import ru.kozirfm.f1news.databinding.FragmentNewsBinding
import ru.kozirfm.f1news.ui.adapters.NewsAdapter
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel
import ru.kozirfm.f1news.ui.viewstates.Data

@Suppress("UNCHECKED_CAST")
class NewsFragment : BaseFragment(R.layout.fragment_news) {

    private val newsViewModel by viewModel<NewsViewModel>()
    override val bottomNavigationVisibility: Int = View.VISIBLE
    private lateinit var binding: FragmentNewsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentNewsBinding.bind(view)

        binding.newsFragmentToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.signInMenuItem -> {
                    findNavController().navigate(R.id.action_nav_news_to_authorizationFragment)
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }
        val newsRecyclerViewAdapter = initRecyclerViewAdapter()
        initRecyclerView(newsRecyclerViewAdapter)
        newsRecyclerViewAdapter.addLoadStateListener { states ->
            if (states.refresh == Loading) {
                startLoading(binding.articlesRecyclerView, binding.articlesProgressBar)
            }
            if (states.prepend.endOfPaginationReached) {
                stopLoading(binding.articlesRecyclerView, binding.articlesProgressBar)
            }
        }

        newsViewModel.getData().observe(viewLifecycleOwner) { viewState ->
            when (viewState) {
                is Data<*> -> {
                    viewState.data?.let {
                        newsRecyclerViewAdapter.submitData(
                            viewLifecycleOwner.lifecycle,
                            it as PagingData<Article>
                        )
                    }
                }
                else -> throw IllegalStateException()
            }
        }
    }

    private fun initRecyclerViewAdapter(): NewsAdapter {
        return NewsAdapter { article ->
            val bottomSheetDialogFragment = NewsTextBottomSheetDialogFragment()
            bottomSheetDialogFragment.arguments = Bundle().apply {
                putString(
                    NewsTextBottomSheetDialogFragment.NEWS_TEXT_ARGUMENTS,
                    article.text
                )
            }
            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, tag
            )
        }
    }

    private fun initRecyclerView(newsRecyclerViewAdapter: NewsAdapter) {
        binding.articlesRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.articlesRecyclerView.setHasFixedSize(true)
        binding.articlesRecyclerView.adapter = newsRecyclerViewAdapter
    }

    override fun onDestroy() {
        println("NewsFragmentOnDestroy")
        super.onDestroy()
    }
}
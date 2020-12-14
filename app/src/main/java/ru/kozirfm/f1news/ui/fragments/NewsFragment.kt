package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.map
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.NewsSimple
import ru.kozirfm.f1news.data.entites.NewsWithImage
import ru.kozirfm.f1news.data.model.ArticleMapper
import ru.kozirfm.f1news.ui.adapters.NewsAdapter
import ru.kozirfm.f1news.ui.viewmodels.NewsViewModel

class NewsFragment : BaseFragment() {

    private val newsViewModel by viewModels<NewsViewModel>()
    override val bottomNavigationVisibility: Int = View.VISIBLE
    override val fragmentLayout: Int = R.layout.fragment_news

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = Bundle()
        val adapter = NewsAdapter { news ->
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
        articlesRecyclerView.adapter = adapter

        newsViewModel.repositoryNews.observe(viewLifecycleOwner) { articles ->
            val news = articles.map { ArticleMapper.mapArticlesToNews(it) }
            adapter.submitData(viewLifecycleOwner.lifecycle, news)
        }
    }
}

package ru.kozirfm.f1news.ui.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_news.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.adapters.ArticlesRecyclerViewAdapter
import ru.kozirfm.f1news.ui.viewmodels.MainViewModel
import ru.kozirfm.f1news.ui.viewstates.MainViewState.ShowArticles
import ru.kozirfm.f1news.ui.viewstates.MainViewState.ShowError

class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by lazy { ViewModelProvider(this).get(MainViewModel::class.java) }
    private val articlesRecyclerViewAdapter: ArticlesRecyclerViewAdapter by lazy { ArticlesRecyclerViewAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        mainActivityToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.signInMenuItem -> {
                    startActivity(Intent(applicationContext, AuthorizationActivity::class.java))
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }
        }

        articlesRecyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        articlesRecyclerView.adapter = articlesRecyclerViewAdapter
        articlesRecyclerView.hasFixedSize()

        mainViewModel.viewState.observe(this, { viewState ->
            when (viewState) {
                is ShowArticles -> viewState.articles?.let {
                    articlesRecyclerViewAdapter.articles = it
                }
                is ShowError -> Toast.makeText(
                    applicationContext,
                    viewState.t?.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }
}

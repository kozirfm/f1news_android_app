package ru.kozirfm.f1news.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.container_with_navigation.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.fragments.BaseFragment
import ru.kozirfm.f1news.ui.fragments.ChampionshipFragment
import ru.kozirfm.f1news.ui.fragments.NewsFragment

class MainActivity : AppCompatActivity(), FragmentManagerChanger {

    init {
        FragmentManager.fragmentManagerChanger = this
    }

    private val newsFragment by lazy { NewsFragment() }
    private val championshipFragment by lazy { ChampionshipFragment() }
    private val bottomMenuBackStack = arrayListOf<BaseFragment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_with_navigation)
        replaceFragment(newsFragment)

        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.newsMenuItem -> {
                    replaceFragment(fragment = newsFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.championshipMenuItem -> {
                    replaceFragment(fragment = championshipFragment)
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }

    override fun replaceFragment(fragment: BaseFragment) {
        if (bottomMenuBackStack.contains(fragment)) {
            bottomMenuBackStack.remove(fragment)
            bottomMenuBackStack.add(fragment)
        } else {
            bottomMenuBackStack.add(fragment)
        }

        supportFragmentManager
            .beginTransaction()
            .replace(
                R.id.containerWithNavigation,
                bottomMenuBackStack.last()
            )
            .commit()
    }

    override fun finishFragment() {
        if (bottomMenuBackStack.last() == newsFragment) {
            finish()
            return
        }
        bottomMenuBackStack.remove(bottomMenuBackStack.last())
        replaceFragment(bottomMenuBackStack.last())
    }

    override fun onBackPressed() {
        finishFragment()
    }
}

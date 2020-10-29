package ru.kozirfm.f1news.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.fragments.NewsFragment

class MainActivity : AppCompatActivity(), IChangeFragmentManager {

    init {
        FragmentManager.changeFragmentManager = this
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.container_with_navigation)
        FragmentManager.replaceFragment(fragment = NewsFragment())
    }

    override fun replaceFragment(fragment: Fragment, tag: String?) {
        if (tag != null) {
            supportFragmentManager
                .beginTransaction()
                .addToBackStack(tag)
                .replace(R.id.containerWithNavigation, fragment)
                .commit()
        } else {
            supportFragmentManager
                .beginTransaction()
                .replace(R.id.containerWithNavigation, fragment)
                .commit()
        }
    }

    override fun finishFragment() {
        supportFragmentManager.popBackStack()
    }


}

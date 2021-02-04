package ru.kozirfm.f1news.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.databinding.ActivityMainBinding

@KoinExperimentalAPI
class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController =
            navHostFragment.navController

        activityMainBinding.bottomNavigationView.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

}

package ru.kozirfm.f1news.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.koin.androidx.fragment.android.setupKoinFragmentFactory
import org.koin.core.KoinExperimentalAPI
import ru.kozirfm.f1news.R

@KoinExperimentalAPI
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setupKoinFragmentFactory()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavView: BottomNavigationView = findViewById(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        bottomNavView.setupWithNavController(navController)

    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

}

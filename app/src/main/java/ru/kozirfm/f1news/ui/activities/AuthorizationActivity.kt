package ru.kozirfm.f1news.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_authorization.*
import ru.kozirfm.f1news.R

class AuthorizationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorization)

        authorizationActivityToolbar.setNavigationOnClickListener {
            this.finish()
        }

        signUpAuthorizationActivityButton.setOnClickListener {
            startActivity(Intent(applicationContext, RegistrationActivity::class.java))
        }

    }
}
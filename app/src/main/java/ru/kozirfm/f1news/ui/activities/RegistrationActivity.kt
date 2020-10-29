package ru.kozirfm.f1news.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_registration.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.viewmodels.RegistrationViewModel

class RegistrationActivity : AppCompatActivity() {

    val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(
            RegistrationViewModel::class.java
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)

        registrationActivityToolbar.setNavigationOnClickListener {
            this.finish()
        }
    }
}
package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.data.entites.User
import ru.kozirfm.f1news.data.repositories.Repository
import ru.kozirfm.f1news.ui.viewmodels.RegistrationViewModel

class RegistrationFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.GONE
    override val fragmentLayout: Int = R.layout.fragment_registration

    val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(
            RegistrationViewModel::class.java
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sendRegistrationDataButton.setOnClickListener {
            Repository.addUser(
                User(
                    registrationUsernameEditText.text.toString(),
                    registrationEmailEditText.text.toString(),
                    registrationPasswordEditText.text.toString()
                )
            )
        }
    }
}

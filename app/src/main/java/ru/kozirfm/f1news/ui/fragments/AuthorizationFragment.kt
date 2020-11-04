package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.fragment_authorization.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.activity.FragmentManager

class AuthorizationFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.GONE
    override val fragmentLayout: Int = R.layout.fragment_authorization
    override val fragmentTag: String = AuthorizationFragment::class.java.name + "TAG"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authorizationFragmentToolbar.setNavigationOnClickListener {
            FragmentManager.finishFragment()
        }

        signUpAuthorizationFragmentButton.setOnClickListener {
            FragmentManager.replaceFragment(RegistrationFragment())
        }
    }
}

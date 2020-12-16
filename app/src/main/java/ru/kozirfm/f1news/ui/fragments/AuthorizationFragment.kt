package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_authorization.*
import ru.kozirfm.f1news.R

class AuthorizationFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.GONE
    override val fragmentLayout: Int = R.layout.fragment_authorization
    override val recyclerView: Int? = null
    override val progressBar: Int? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        authorizationFragmentToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        signUpAuthorizationFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }

    }


}

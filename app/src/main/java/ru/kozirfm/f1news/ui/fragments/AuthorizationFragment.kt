package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.databinding.FragmentAuthorizationBinding

class AuthorizationFragment : BaseFragment(R.layout.fragment_authorization) {

    override val bottomNavigationVisibility: Int = View.GONE

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAuthorizationBinding = FragmentAuthorizationBinding.bind(view)

        fragmentAuthorizationBinding.authorizationFragmentToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        fragmentAuthorizationBinding.signUpAuthorizationFragmentButton.setOnClickListener {
            findNavController().navigate(R.id.action_authorizationFragment_to_registrationFragment)
        }

    }


}

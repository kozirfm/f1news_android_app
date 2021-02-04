package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.databinding.FragmentRegistrationBinding
import ru.kozirfm.f1news.ui.viewmodels.RegistrationViewModel

class RegistrationFragment : BaseFragment(R.layout.fragment_registration) {

    override val bottomNavigationVisibility: Int = View.GONE

    val registrationViewModel by viewModels<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentRegistrationBinding = FragmentRegistrationBinding.bind(view)

        fragmentRegistrationBinding.registrationFragmentToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        fragmentRegistrationBinding.sendRegistrationDataButton.setOnClickListener {

        }
    }
}

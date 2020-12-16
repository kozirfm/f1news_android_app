package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.viewmodels.RegistrationViewModel

class RegistrationFragment : BaseFragment() {

    override val bottomNavigationVisibility: Int = View.GONE
    override val fragmentLayout: Int = R.layout.fragment_registration
    override val recyclerView: Int? = null
    override val progressBar: Int? = null

    val registrationViewModel by viewModels<RegistrationViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        registrationFragmentToolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        sendRegistrationDataButton.setOnClickListener {

        }
    }
}

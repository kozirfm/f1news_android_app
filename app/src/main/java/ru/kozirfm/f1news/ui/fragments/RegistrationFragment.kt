package ru.kozirfm.f1news.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_registration.*
import ru.kozirfm.f1news.R
import ru.kozirfm.f1news.ui.activity.FragmentManager
import ru.kozirfm.f1news.ui.viewmodels.RegistrationViewModel

class RegistrationFragment : BaseFragment() {

    companion object {
        val TAG = RegistrationFragment::class.java.name + "TAG"
    }

    val registrationViewModel: RegistrationViewModel by lazy {
        ViewModelProvider(this).get(
            RegistrationViewModel::class.java
        )
    }

    override val bottomNavigationVisibility: Int = View.GONE
    override val fragmentLayout: Int = R.layout.fragment_registration

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registrationActivityToolbar.setNavigationOnClickListener {
            FragmentManager.finishFragment()
        }
    }
}

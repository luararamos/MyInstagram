package com.example.myinstagram.register.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.FragmentRegisterEmailBinding
import com.example.myinstagram.register.RegisterEmail
import com.example.myinstagram.register.presentation.RegisterEmailPresenter

class RegisterEmailFragment : Fragment(R.layout.fragment_register_email), RegisterEmail.View {
    private var binding: FragmentRegisterEmailBinding? = null

    override lateinit var presenter: RegisterEmail.Presenter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterEmailBinding.bind(view)
        presenter = RegisterEmailPresenter(this, DependencyInjector.registerEmailRepository())

        binding?.let {
            with(it) {
                txtHaveAccount.setOnClickListener {
                    activity?.finish()
                }
                editEmail.addTextChangedListener(watcher)
                editEmail.addTextChangedListener(TxtWatcher{
                    displayEmailFailure(null)

                })
                btnNext.setOnClickListener {
                    presenter.create(editEmail.text.toString())
                }
            }
        }
    }

    private val watcher = TxtWatcher {
        binding?.btnNext?.isEnabled = binding?.editEmail?.text.toString().isNotEmpty()
    }

    override fun onDestroy() {
        binding = null
        presenter.onDestroy()
        super.onDestroy()
    }

    override fun showProgress(enabled: Boolean) {
        binding?.btnNext?.showProgressBar(enabled)
    }


    override fun displayEmailFailure(emailError: Int?) {
        binding?.textInputLayoutEmail?.error = emailError?.let { getString(it) }
    }

    override fun onEmailFailure(message: String) {
        binding?.textInputLayoutEmail?.error = message
    }

    override fun goToNamePasswordScreen(email: String) {
        // depois mandamos para a prox tela
    }
}
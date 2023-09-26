package com.example.myinstagram.register.view

import android.os.Bundle
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myinstagram.R
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.FragmentRegisterEmailBinding
import com.example.myinstagram.databinding.FragmentRegisterNamePasswordBinding
import com.example.myinstagram.register.RegisterNameAndPassword
import com.example.myinstagram.register.presentation.RegisterNameAndPasswordPresenter
import java.lang.IllegalArgumentException

class RegisterNamePasswordFragment: Fragment(), RegisterNameAndPassword.View {
    override lateinit var presenter: RegisterNameAndPassword.Presenter
    private var binding: FragmentRegisterNamePasswordBinding? = null
    private var fragmentAttachListener: FragmentAttachListener? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_register_name_password, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val repository = DependencyInjector.registerEmailRepository()
        presenter =RegisterNameAndPasswordPresenter(this, repository)
        binding = FragmentRegisterNamePasswordBinding.bind(view)
        val email = arguments?.getString(KEY_EMAIL)?: throw  IllegalArgumentException("email not found")

        binding?.let {
            with(it){
                registerTxtLogin.setOnClickListener {
                    activity?.finish()
                }
                registerNameBtnNext.setOnClickListener {
                    presenter.create(
                        email,
                        editNome.text.toString(),
                        editPassword.text.toString(),
                        editPasswordConfirm.text.toString()
                    )
                }

                editNome.addTextChangedListener(watcher)
                editPassword.addTextChangedListener(watcher)
                editPasswordConfirm.addTextChangedListener(watcher)

                editNome.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })
                editPassword.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })
                editPasswordConfirm.addTextChangedListener(TxtWatcher{
                    displayNameFailure(null)
                })
            }
        }
        Log.i("teste", email.toString())
    }

    override fun showProgress(enabled: Boolean) {
        binding?.registerNameBtnNext?.showProgressBar(enabled)
    }

    override fun displayNameFailure(nameError: Int?) {
        binding?.editNameRegisterInput?.error = nameError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding?.editPasswordRegisterInput?.error = passwordError?.let { getString(it) }
    }

    override fun onCreateFailure(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
    }

    private val watcher = TxtWatcher {
        binding?.registerNameBtnNext?.isEnabled = binding?.editNome?.text.toString().isNotEmpty()
                && binding?.editPassword?.text.toString().isNotEmpty()
                && binding?.editPasswordConfirm?.text.toString().isNotEmpty()
    }

    override fun onCreateSuccess(name: String) {
       // abrir tela bem - vindo
    }

    override fun onDestroy() {
        binding = null
        fragmentAttachListener = null
        presenter.onDestroy()
        super.onDestroy()
    }
    companion object{
        const val KEY_EMAIL = "key_email"
    }


}
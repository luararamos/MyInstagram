package com.example.myinstagram.login.view

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.login.Login
import com.example.myinstagram.login.presentation.LoginPresenter

class LoginActivity : AppCompatActivity(), Login.View {
    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = LoginPresenter(this)
        presenter.login("", "")

        with(binding) {
            editEmail.addTextChangedListener(watcher)
            editEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })
            editPassword.addTextChangedListener(watcher)
            editPassword.addTextChangedListener(TxtWatcher{
                displayPasswordFailure(null)
            })

            btnLogin.setOnClickListener {
                presenter.login(editEmail.toString(), editPassword.toString())
            }
//            Handler(Looper.getMainLooper()).postDelayed({
//                btnLogin.showProgressBar(false)
//            }, 2000)
        }

    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }

    private val watcher = TxtWatcher {
        binding.btnLogin.isEnabled = binding.editEmail.text.toString().isNotEmpty()
                && binding.editPassword.text.toString().isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnLogin.showProgressBar(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.editEmail.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.editPassword.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        // Ir para tela inicial
    }

    override fun onUserUnauthorized() {
        // Mostrar um alerta
    }
}
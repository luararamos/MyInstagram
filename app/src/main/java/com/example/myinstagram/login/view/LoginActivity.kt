package com.example.myinstagram.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.view.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.login.Login

class LoginActivity : AppCompatActivity(), Login.View {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            editEmail.addTextChangedListener(watcher)
            editPassword.addTextChangedListener(watcher)

            btnLogin.setOnClickListener {


                editEmail.error = "Esse e-mail é inválido"
                editPassword.error = "Senha incorreta"
            }
            Handler(Looper.getMainLooper()).postDelayed({
                btnLogin.showProgressBar(false)
            }, 2000)
        }

    }

    private val watcher = TxtWatcher {
        binding.btnLogin.isEnabled = it.isNotEmpty()
    }

    override fun showProgress(enabled: Boolean) {
        binding.btnLogin.showProgressBar(enabled)
    }

    override fun displayEmailFailure(emailError: Int?) {
        binding.editEmail.error = emailError?.let {
            getString(it)
        }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.editPassword.error = passwordError?.let {
            getString(it)
        }
    }

    override fun onUserAutheticated() {
        // Ir para tela inicial
    }

    override fun onUserUnauthorized() {
        // Mostrar um alerta
    }
}
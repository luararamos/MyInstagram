package com.example.myinstagram.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.view.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            editEmail.addTextChangedListener(watcher)
            editPassword.addTextChangedListener(watcher)

            btnLogin.setOnClickListener {

                btnLogin.showProgressBar(true)
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
}
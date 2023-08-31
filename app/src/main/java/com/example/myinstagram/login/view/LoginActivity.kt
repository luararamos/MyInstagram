package com.example.myinstagram.login.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.editEmail.addTextChangedListener(watcher)
        binding.editPassword.addTextChangedListener(watcher)

        binding.btnLogin.setOnClickListener {
            binding.btnLogin.showProgressBar(true)
            binding.editEmail.error = "Esse e-mail é inválido"
            binding.editPassword.error = "Senha incorreta"
            Handler(Looper.getMainLooper()).postDelayed({
                binding.btnLogin.showProgressBar(false)
            },2000)
        }

    }

    private val watcher = object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            binding.btnLogin.isEnabled = s.toString().isNotEmpty()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }
}
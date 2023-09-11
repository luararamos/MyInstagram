package com.example.myinstagram.login.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myinstagram.common.base.DependencyInjector
import com.example.myinstagram.common.util.TxtWatcher
import com.example.myinstagram.databinding.ActivityLoginBinding
import com.example.myinstagram.login.Login
import com.example.myinstagram.login.presentation.LoginPresenter
import com.example.myinstagram.main.view.MainActivity

class LoginActivity : AppCompatActivity(), Login.View {
    private lateinit var binding: ActivityLoginBinding

    override lateinit var presenter: Login.Presenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Aqui View ->  Presenter
        presenter = LoginPresenter(this, DependencyInjector.loginRepository())

        with(binding) {
            editEmail.addTextChangedListener(watcher)
            editEmail.addTextChangedListener(TxtWatcher {
                displayEmailFailure(null)
            })
            editPassword.addTextChangedListener(watcher)
            editPassword.addTextChangedListener(TxtWatcher {
                displayPasswordFailure(null)
            })

            btnLogin.setOnClickListener {
                presenter.login(editEmail.text.toString(), editPassword.text.toString())
            }

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
        binding.textInputLayoutEmail.error = emailError?.let { getString(it) }
    }

    override fun displayPasswordFailure(passwordError: Int?) {
        binding.textInputLayoutPassword.error = passwordError?.let { getString(it) }
    }

    override fun onUserAuthenticated() {
        val intent = Intent(this,MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    override fun onUserUnauthorized(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
package com.example.myinstagram.register.presentation

import android.util.Patterns
import com.example.myinstagram.R
import com.example.myinstagram.common.model.UserAuth
import com.example.myinstagram.login.Login
import com.example.myinstagram.login.data.LoginCallback
import com.example.myinstagram.login.data.LoginRepository
import com.example.myinstagram.register.RegisterEmail
import com.example.myinstagram.register.data.RegisterEmailCallback
import com.example.myinstagram.register.data.RegisterEmailRepository

class RegisterEmailPresenter(
    private var view: RegisterEmail.View?,
    private val repository: RegisterEmailRepository
) : RegisterEmail.Presenter{
    override fun create(email: String) {
        val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
        if (!isEmailValid) {
            view?.displayEmailFailure(R.string.invalid_email)
        } else {
            view?.displayEmailFailure(null)
        }

        if (isEmailValid) {
            view?.showProgress(true)
            repository.create(email, object : RegisterEmailCallback {
                override fun onSuccess(){
                    view?.goToNamePasswordScreen(email)
                }

                override fun onFailure(message: String) {
                    view?.displayEmailFailure(R.string.already_register_email)
                }

                override fun onComplete() {
                    view?.showProgress(false)
                }
            })
        }
    }

    override fun onDestroy() {
        view = null
    }

}
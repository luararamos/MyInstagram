package com.example.myinstagram.login

import androidx.annotation.StringRes
import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface Login {
    // camada presenter
    interface Presenter : BasePresenter {
        fun login(email: String, password: String)
    }

    //camada de view
    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun displayPasswordFailure(@StringRes passwordError: Int?)
        fun onUserAuthenticated()
        fun onUserUnauthorized(message: String)
    }


}
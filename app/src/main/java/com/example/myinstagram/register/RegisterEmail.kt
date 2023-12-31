package com.example.myinstagram.register

import androidx.annotation.StringRes
import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface RegisterEmail {
    interface Presenter : BasePresenter {
        fun create(email: String)

    }

    interface View : BaseView<Presenter> {

        fun showProgress(enabled: Boolean)
        fun displayEmailFailure(@StringRes emailError: Int?)
        fun onEmailFailure(message: String)
        fun goToNamePasswordScreen(email: String)

    }
}
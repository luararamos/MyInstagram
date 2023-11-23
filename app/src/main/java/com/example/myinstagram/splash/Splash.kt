package com.example.myinstagram.splash

import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface Splash {
    interface Presenter: BasePresenter{
        fun authenticated()
    }

    interface View: BaseView<Presenter>{
        fun goToMainScreen()
        fun goToLoginScreen()
    }
}
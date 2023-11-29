package com.example.myinstagram.common.view

import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView
import com.example.myinstagram.profile.view.Profile

interface Home {
    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPost()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enebled:Boolean)
    }
}
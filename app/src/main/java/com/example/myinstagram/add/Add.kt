package com.example.myinstagram.add

import android.net.Uri
import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface Add {
    interface Presenter : BasePresenter {
        fun createPost(uri: Uri, caption: String)

    }

    interface View : BaseView<Presenter> {
        fun showProgress(enabled: Boolean)
        fun displayRequestSuccess()
        fun displayRequestFailure(message: String)

    }
}
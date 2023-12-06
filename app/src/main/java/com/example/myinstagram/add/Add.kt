package com.example.myinstagram.add

import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView

interface Add {
    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {

    }
}
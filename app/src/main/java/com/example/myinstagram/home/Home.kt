package com.example.myinstagram.home

import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

interface Home {
    interface Presenter : BasePresenter {
        fun fetchFeed()
        fun clear()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enebled:Boolean)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPosts(posts: List<Post>)
    }
}
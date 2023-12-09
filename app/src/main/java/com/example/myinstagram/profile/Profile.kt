package com.example.myinstagram.profile

import com.example.myinstagram.common.base.BasePresenter
import com.example.myinstagram.common.base.BaseView
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

interface Profile {
    interface Presenter : BasePresenter {
        fun fetchUserProfile()
        fun fetchUserPost()
        fun clear()
    }

    interface View : BaseView<Presenter> {
        fun showProgress(enebled:Boolean)
        fun displayUserProfile(userAuth: UserAuth)
        fun displayRequestFailure(message: String)
        fun displayEmptyPost()
        fun displayFullPosts(posts: List<Post>)

    }
}
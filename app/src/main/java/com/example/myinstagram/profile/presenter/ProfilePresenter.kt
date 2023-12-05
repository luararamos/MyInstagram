package com.example.myinstagram.profile.presenter

import android.util.Patterns
import com.example.myinstagram.R
import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.Database
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth
import com.example.myinstagram.profile.Profile
import com.example.myinstagram.profile.data.ProfileRepository
import com.example.myinstagram.register.RegisterEmail
import com.example.myinstagram.register.data.RegisterCallback
import com.example.myinstagram.register.data.RegisterRepository
import java.lang.RuntimeException

class ProfilePresenter(
    private var view: Profile.View?,
    private val repository: ProfileRepository
) : Profile.Presenter{

    override fun fetchUserProfile() {
        view?.showProgress(true)
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserProfile(object : RequestCallback<UserAuth>{
            override fun onSuccess(data: UserAuth) {
                view?.displayUserProfile(data)
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {

            }

        })
    }

    override fun fetchUserPost() {
        val userUUID = Database.sessionAuth?.uuid ?: throw RuntimeException("user not found")
        repository.fetchUserPosts(object : RequestCallback<List<Post>>{
            override fun onSuccess(data: List<Post>) {
                if(data.isEmpty()){
                    view?.displayEmptyPost()
                } else{
                    view?. displayFullPosts(data)
                }
            }

            override fun onFailure(message: String) {
                view?.displayRequestFailure(message)
            }

            override fun onComplete() {
                view?.showProgress(false)
            }

        })
    }



    override fun onDestroy() {
        view = null
    }

}
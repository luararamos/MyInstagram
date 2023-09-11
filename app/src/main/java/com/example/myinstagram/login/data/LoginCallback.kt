package com.example.myinstagram.login.data

import com.example.myinstagram.common.model.UserAuth

interface LoginCallback {
    fun onSuccess(userAuth: UserAuth)
    fun onFailure(message: String)
    fun onComplete()
}
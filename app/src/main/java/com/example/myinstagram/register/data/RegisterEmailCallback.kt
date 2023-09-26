package com.example.myinstagram.register.data

interface RegisterEmailCallback {
    fun onSuccess()
    fun onFailure(message: String)
    fun onComplete()
}
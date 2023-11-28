package com.example.myinstagram.splash.data

import com.example.myinstagram.common.model.Database

class FakeLocalDataSource: SplashDataSource {
    override fun session(callback: SplashCallback) {
        if (Database.sessionAuth != null){
            callback.onSuccess()
        } else{
            callback.onFailure()
        }
    }
}
package com.example.myinstagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.myinstagram.common.model.Database

class FakeRegisterEmailDataSource : RegisterEmailDataSource {
    override fun create(email: String, callback: RegisterEmailCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // SELECT * FROM USER_AUTH WHERE EMAIL = ? LIMIT 1
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null){
                callback.onSuccess()
            }else{
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }
}
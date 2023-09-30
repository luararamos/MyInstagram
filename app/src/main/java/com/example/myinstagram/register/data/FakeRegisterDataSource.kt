package com.example.myinstagram.register.data

import android.os.Handler
import android.os.Looper
import com.example.myinstagram.common.model.Database
import com.example.myinstagram.common.model.UserAuth
import java.util.UUID

class FakeRegisterDataSource : RegisterDataSource {
    override fun create(email: String, callback: RegisterCallback) {
        Handler(Looper.getMainLooper()).postDelayed({
            // SELECT * FROM USER_AUTH WHERE EMAIL = ? LIMIT 1
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }

            if (userAuth == null) {
                callback.onSuccess()
            } else {
                callback.onFailure("Usuário já cadastrado")
            }
            callback.onComplete()
        }, 2000)
    }

    override fun create(
        email: String,
        name: String,
        password: String,
        callback: RegisterCallback
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            val userAuth = Database.usersAuth.firstOrNull { email == it.email }


            if (userAuth != null) {
                callback.onFailure("Usuário já cadastrado")
            } else {
                val newUser = UserAuth(UUID.randomUUID().toString(), name, email, password)
                val created = Database.usersAuth.add(newUser)
                if (created) {
                    Database.sessionAuth = newUser
                    callback.onSuccess()
                } else {
                    callback.onFailure("Erro interno no servidor.")
                }
            }
            callback.onComplete()
        }, 2000)
    }


}
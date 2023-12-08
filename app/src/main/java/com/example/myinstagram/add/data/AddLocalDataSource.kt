package com.example.myinstagram.add.data

import com.example.myinstagram.common.model.Database
import com.example.myinstagram.common.model.UserAuth
import java.lang.RuntimeException

class AddLocalDataSource : AddDataSource {
    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!!")
    }
}
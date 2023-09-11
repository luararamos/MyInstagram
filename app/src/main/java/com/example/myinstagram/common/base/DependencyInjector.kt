package com.example.myinstagram.common.base

import com.example.myinstagram.login.data.FakeDataSource
import com.example.myinstagram.login.data.LoginRepository

object DependencyInjector {
    fun loginRepository(): LoginRepository{
        return LoginRepository(FakeDataSource())
    }
}
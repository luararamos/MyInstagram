package com.example.myinstagram.common.base

import com.example.myinstagram.login.data.FakeDataSource
import com.example.myinstagram.login.data.LoginRepository
import com.example.myinstagram.register.data.FakeRegisterDataSource
import com.example.myinstagram.register.data.RegisterRepository
import com.example.myinstagram.splash.data.FakeLocalDataSource
import com.example.myinstagram.splash.data.SplashRepository

object DependencyInjector {

    fun splashRepository(): SplashRepository{
        return SplashRepository(FakeLocalDataSource())
    }
    fun loginRepository(): LoginRepository{
        return LoginRepository(FakeDataSource())
    }

    fun registerEmailRepository(): RegisterRepository{
        return RegisterRepository(FakeRegisterDataSource())
    }
}
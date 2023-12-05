package com.example.myinstagram.common.base

import com.example.myinstagram.home.data.FeedMemoryCache
import com.example.myinstagram.home.data.HomeDataSourceFactory
import com.example.myinstagram.home.data.HomeRepository
import com.example.myinstagram.login.data.FakeDataSource
import com.example.myinstagram.login.data.LoginRepository
import com.example.myinstagram.profile.data.PostListMemoryCache
import com.example.myinstagram.profile.data.ProfileDataSourceFactory
import com.example.myinstagram.profile.data.ProfileFakeRemoteDataSource
import com.example.myinstagram.profile.data.ProfileMemoryCache
import com.example.myinstagram.profile.data.ProfileRepository
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

    fun profileRepository(): ProfileRepository {
        return ProfileRepository(
            ProfileDataSourceFactory(ProfileMemoryCache, PostListMemoryCache)
        )
    }

    fun homeRepository(): HomeRepository {
        return HomeRepository(HomeDataSourceFactory(FeedMemoryCache))
    }
}
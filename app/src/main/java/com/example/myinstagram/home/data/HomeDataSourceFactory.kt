package com.example.myinstagram.home.data

import com.example.myinstagram.common.base.Cache
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

class HomeDataSourceFactory(
    private val feedCache: Cache<List<Post>>
) {
    fun createLocalDataSource(): HomeDataSource {
        return HomeLocalDataSource(feedCache)
    }

    fun createFromFeed(): HomeDataSource {
        if (feedCache.isCached()){
            return HomeLocalDataSource(feedCache)
        }
        return HomeFakeRemoteDataSource()
    }
}
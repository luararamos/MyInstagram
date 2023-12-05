package com.example.myinstagram.home.data

import com.example.myinstagram.common.base.Cache
import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.Database
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

class HomeLocalDataSource( private val feedCache: Cache<List<Post>>) : HomeDataSource {

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        val posts = feedCache.get(userUUID)
        if (posts != null) {
            callback.onSuccess(posts)
        } else {
            callback.onFailure("posts não existem")
        }
        callback.onComplete()
    }

    override fun fetchSession(): UserAuth {
        return Database.sessionAuth ?: throw RuntimeException("Usuário não logado!")
    }

    override fun putFeed(response: List<Post>) {
        feedCache.put(response)
    }
}
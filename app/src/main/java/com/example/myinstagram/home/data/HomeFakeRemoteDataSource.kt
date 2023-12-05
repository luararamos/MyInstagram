package com.example.myinstagram.home.data

import android.os.Handler
import android.os.Looper
import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.Database
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

class HomeFakeRemoteDataSource : HomeDataSource{

    override fun fetchFeed(userUUID: String, callback: RequestCallback<List<Post>>) {
        Handler(Looper.getMainLooper()).postDelayed({
            val feed = Database.feeds[userUUID]

            callback.onSuccess(feed?.toList()?: emptyList())

            callback.onComplete()
        }, 2000)
    }

}
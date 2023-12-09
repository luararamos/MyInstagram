package com.example.myinstagram.profile.data

import com.example.myinstagram.common.base.Cache
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

object PostListMemoryCache : Cache<List<Post>> {
    private var posts: List<Post>? = null

    override fun isCached(): Boolean {
        return  posts != null
    }

    override fun get(key: String): List<Post>? {
        return posts
    }

    override fun put(data: List<Post>?) {
        posts = data
    }
}
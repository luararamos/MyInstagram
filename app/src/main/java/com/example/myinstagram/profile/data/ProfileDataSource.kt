package com.example.myinstagram.profile.data

import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth

interface ProfileDataSource {
    fun fetchUserProfile(userUUID: String, callback: RequestCallback<UserAuth>)
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>)
    fun fetchSession(): UserAuth { throw UnsupportedOperationException() }
    fun putUser(response: UserAuth) { throw UnsupportedOperationException() }
    fun putPosts(response: List<Post>?) { throw UnsupportedOperationException() }

}
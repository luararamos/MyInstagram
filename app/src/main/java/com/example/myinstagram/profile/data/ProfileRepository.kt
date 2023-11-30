package com.example.myinstagram.profile.data

import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.Post
import com.example.myinstagram.common.model.UserAuth
import java.util.UUID

class ProfileRepository(private val dataSource: ProfileDataSource) {

    fun fetchUserProfile(useUUID: String, callback: RequestCallback<UserAuth>){
        dataSource.fetchUserProfile(useUUID,callback)
    }
    fun fetchUserPosts(userUUID: String, callback: RequestCallback<List<Post>>){
        dataSource.fetchUserPosts(userUUID, callback)
    }

}
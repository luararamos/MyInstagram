package com.example.myinstagram.add.data

import android.net.Uri
import com.example.myinstagram.common.base.RequestCallback
import com.example.myinstagram.common.model.UserAuth

interface AddDataSource {
    fun createPost(userUUID: String, uri: Uri, caption: String, callback: RequestCallback<Boolean>){ throw  UnsupportedOperationException()}

    fun fetchSession(): UserAuth { throw  UnsupportedOperationException()}
}
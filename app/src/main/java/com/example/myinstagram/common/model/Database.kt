package com.example.myinstagram.common.model

import java.util.UUID

object Database {
    val usersAuth = hashSetOf<UserAuth>()
    val photos = hashSetOf<Photo>()
    val posts = hashMapOf<String, Set<Post>>()
    val feeds = hashMapOf<String, Set<Post>>()

    var sessionAuth: UserAuth? = null

    init {
        usersAuth.add(UserAuth(UUID.randomUUID().toString(),"UserA","userA@gmail.com","12345678"))
        usersAuth.add(UserAuth(UUID.randomUUID().toString(),"UserB","userB@gmail.com","87654321"))

        sessionAuth = usersAuth.first()
    }
}
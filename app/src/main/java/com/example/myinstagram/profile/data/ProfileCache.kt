package com.example.myinstagram.profile.data

import java.util.UUID

interface ProfileCache<T> {

    fun isCached() : Boolean

    fun get(key: String) : T?

    fun put(data: T)

}
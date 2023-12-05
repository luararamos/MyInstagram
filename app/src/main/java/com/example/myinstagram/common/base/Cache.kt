package com.example.myinstagram.common.base

interface Cache<T> {

    fun isCached() : Boolean

    fun get(key: String) : T?

    fun put(data: T)

}
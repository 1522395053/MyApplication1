package com.example.myapplication1.model

class Test {
    private val user: User? = null

    fun getUserName(): String? {
        return user?.name
    }

    fun getUserName2(): String {
        return user?.name ?: ""
    }
}

class User(val name: String)
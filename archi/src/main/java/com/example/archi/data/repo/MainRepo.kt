package com.example.archi.data.repo

import com.example.archi.data.api.ApiService


class MainRepository(private val apiService: ApiService) {

    suspend fun getUsers() = apiService.getUsers()

}

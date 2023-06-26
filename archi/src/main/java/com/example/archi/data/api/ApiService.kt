package com.example.archi.data.api


import com.example.archi.data.model.User
import com.example.archi.data.model.UserListRsp
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET


interface ApiService {

//    @GET("api/user/1")
//    suspend fun getUsers(): List<User>

    @GET("api/user/1")
    suspend fun getUsers(): UserListRsp

    @GET("api/user/1")
    fun getUsers2(): Call<ResponseBody>
}


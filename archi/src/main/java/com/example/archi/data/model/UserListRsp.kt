package com.example.archi.data.model

import com.squareup.moshi.Json

data class UserListRsp(
    @Json(name = "data")
    val cdata: Data,
    val support: Support
)

data class Data(
    val color: String,
    val id: Int,
    val name: String,
    val pantone_value: String,
    val year: Int
)

data class Support(
    val text: String,
    val url: String
)
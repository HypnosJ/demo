package com.jacky.demohomework.data.model

import com.google.gson.annotations.SerializedName

data class LoginReq(
    val username: String,
    val password: String
)
package com.jacky.demohomework.data.api

import com.jacky.demohomework.data.model.LoginReq
import com.jacky.demohomework.data.model.LoginRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface LoginService {
    @POST("/api/login")
    suspend fun postLogin(
        @Header("X-Parse-Application-Id") applicationId: String,
        @Body requestBody: LoginReq
    ): Response<LoginRes>
}
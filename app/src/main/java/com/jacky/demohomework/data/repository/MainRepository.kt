package com.jacky.demohomework.data.repository

import com.jacky.demohomework.Constants
import com.jacky.demohomework.data.api.RetrofitService
import com.jacky.demohomework.data.model.LoginReq
import com.jacky.demohomework.data.model.UpdateUserTimeZoneReq

class MainRepository constructor(private val retrofitService: RetrofitService) {

    suspend fun doLogin(requestBody: LoginReq) =
        retrofitService.loginService.postLogin(Constants.APPLICATION_ID, requestBody)

    suspend fun doUpdateUserTimeZone(sessionToken: String, objectId: String, requestBody: UpdateUserTimeZoneReq) =
        retrofitService.userService.updateUserTimeZone(Constants.APPLICATION_ID, sessionToken, objectId, requestBody)
}
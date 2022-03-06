package com.jacky.demohomework.data.api

import com.jacky.demohomework.data.model.UpdateUserTimeZoneReq
import com.jacky.demohomework.data.model.UpdateUserTimeZoneRes
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PUT
import retrofit2.http.Path

interface UserService {
    @PUT("/api/users/{objectId}")
    suspend fun updateUserTimeZone(
        @Header("X-Parse-Application-Id") applicationId: String,
        @Header("X-Parse-Session-Token") sessionToken: String,
        @Path("objectId") objectId: String,
        @Body requestBody: UpdateUserTimeZoneReq
    ): Response<UpdateUserTimeZoneRes>
}
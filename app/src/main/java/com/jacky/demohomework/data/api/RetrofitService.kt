package com.jacky.demohomework.data.api

import com.jacky.demohomework.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitService {
    private var retrofit: Retrofit? = null
    private const val BASE_URL: String = BuildConfig.APP_BASE_URL

    private fun getRetrofit(): Retrofit {
        if (retrofit == null) {
            val okHttpClient = OkHttpClient.Builder()
                .callTimeout(5, TimeUnit.SECONDS)
                .connectTimeout(5, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .build()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
        }
        return retrofit!!
    }

    val loginService: LoginService = getRetrofit().create(LoginService::class.java)
    val userService: UserService = getRetrofit().create(UserService::class.java)
}
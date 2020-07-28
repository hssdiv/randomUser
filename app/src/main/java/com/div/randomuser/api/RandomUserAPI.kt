package com.div.randomuser.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RandomUserAPI{
    @GET("api")
    fun getUser(@Query("results") amount: String ): Call<UserResponse>
}
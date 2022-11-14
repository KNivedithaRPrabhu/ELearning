package com.robosoft.elearning.api

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SignupRestApi {
    @Headers("Content-Type: application/json")
    @POST("/api/v1/users/signup")
    fun addUser(@Body userData: SignupDataClass): Call<ResponseDataClass>
}

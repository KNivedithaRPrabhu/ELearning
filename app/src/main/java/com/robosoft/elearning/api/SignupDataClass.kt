package com.robosoft.elearning.api

import com.google.gson.annotations.SerializedName

data class SignupDataClass(
    @SerializedName("name") var userName : String? ,
    @SerializedName("email") val email : String?,
    @SerializedName("password") val userPassword : String?,
)

package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    var email: String? = "",
    var password: String? = "",
    @SerializedName("auth_token") var token: String? = ""
)
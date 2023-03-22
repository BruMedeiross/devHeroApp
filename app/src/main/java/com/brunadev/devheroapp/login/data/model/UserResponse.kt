package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("args")var args: User? = null
)
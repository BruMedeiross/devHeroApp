package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName

data class UserResponse(
    @SerializedName("args")var args: Args? = null,
    @SerializedName("data")var data: String? = null,
    @SerializedName("files")var files: Files? = null,
    @SerializedName("form") var form: Form? = null,
    @SerializedName("headers")var headers: HeadersLogon? = null,
    @SerializedName("json")var json: Object? = null,
    @SerializedName("origin")var origin: String? = null,
    @SerializedName("url")var url: String? = null
)
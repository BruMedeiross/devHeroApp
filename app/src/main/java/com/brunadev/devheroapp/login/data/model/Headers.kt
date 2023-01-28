package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName

class Headers (
    @SerializedName("Accept") val acept0:String? = null,
    @SerializedName("Accept-Encoding") val acept1:String? = null,
    @SerializedName("Postman-Token") val acept2:String? = null,
    @SerializedName("User-Agent") val acept3:String? = null,
    @SerializedName("X-Amzn-Trace-Id")val acept4:String? = null
    )

class HeadersLogon (
    @SerializedName("Accept") val acept0:String? = null,
    @SerializedName("Accept-Encoding") val acept1:String? = null,
    @SerializedName("Content-Length") val acept2:String? = null,
    @SerializedName("Host") val acept3:String? = null,
    @SerializedName("Postman-Token") val acept4:String? = null,
    @SerializedName("User-Agent") val acept5:String? = null,
    @SerializedName("X-Amzn-Trace-Id")val acept6:String? = null
)

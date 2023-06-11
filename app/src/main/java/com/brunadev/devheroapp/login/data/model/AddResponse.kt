package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName

data class AddResponse(
    val user: String? = "",
    val title: String? = "",
    val desc: String? = "",
    val priority: Int? = 0,
    @SerializedName("created_dt")val createDate: String? = ""
)

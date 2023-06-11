package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName


data class Project(
    val title: String? = "",
    val desc: String? = "",
    val priority: Int? = 0,
)
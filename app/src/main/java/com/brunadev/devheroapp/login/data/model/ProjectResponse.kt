package com.brunadev.devheroapp.login.data.model

import com.google.gson.annotations.SerializedName


data class GetAllProjects(
    val list: List<Projects>
)

data class Projects(
    val title: String? = "",
    val desc: String? = "",
    val priority: Int? = 0,
    @SerializedName("created_dt") val data: String? = "",
    val user: String = ""
)
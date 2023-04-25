package com.brunadev.devheroapp.login.data.model


data class GetAllProjects(
    val list: List<Projects>
)

data class Projects(
    val id: String? = "",
    val company: String? = "",
    val email: String? = "",
    val logo: Int? = 0,
    val hours: String? = "",
    val data: Long

)
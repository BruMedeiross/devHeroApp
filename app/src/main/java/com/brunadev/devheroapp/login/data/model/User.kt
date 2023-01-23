package com.brunadev.devheroapp.login.data.model

data class User(
    var id: String? = "",
    var name: String? = "",
    var email: String? = "",
    var password: String? = "",
    var passwordConfirm: String? = ""
)


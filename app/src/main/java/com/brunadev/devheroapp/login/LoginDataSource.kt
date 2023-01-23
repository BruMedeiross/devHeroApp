package com.brunadev.devheroapp.login

interface LoginDataSource {
    fun login(email: String, password: String)
}
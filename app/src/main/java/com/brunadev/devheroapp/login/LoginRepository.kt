package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData

import com.brunadev.devheroapp.login.data.model.LoginResponse
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse


interface LoginRepository {

    fun loginUser (user: User?) : LiveData<UserResponse?>

}
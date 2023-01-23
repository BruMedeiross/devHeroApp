package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.data.model.User

interface NewUserRepository {

    fun newUser (user: User?) : LiveData<UserResponse?>

}
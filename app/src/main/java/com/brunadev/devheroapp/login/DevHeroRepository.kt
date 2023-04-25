package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData
import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse


interface DevHeroRepository {

    fun loginUser (user: User) : LiveData<UserResponse?>
    fun newUser (user: User) : LiveData<UserResponse?>
    fun getProjects () : LiveData<List<Projects>>
}
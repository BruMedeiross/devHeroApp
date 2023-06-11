package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData
import com.brunadev.devheroapp.login.data.model.*


interface DevHeroRepository {

    fun loginUser (user: User) : LiveData<UserResponse?>
    fun newUser (user: NewUser) : LiveData<NewUserResponser?>
    fun getProjects () : LiveData<List<Projects>>
    fun getProjectsAPI () : LiveData<List<Projects>?>
    fun addProjects(addRequest: AddRequest) : LiveData<AddResponse?>
}
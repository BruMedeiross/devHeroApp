package com.brunadev.devheroapp.login.viewmodel

import androidx.lifecycle.ViewModel
import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.DevHeroRepository


class HomeViewModel(private val repository: DevHeroRepository = DevHeroImpl()) : ViewModel() {

    fun getAllProjects() = repository.getProjects()
}
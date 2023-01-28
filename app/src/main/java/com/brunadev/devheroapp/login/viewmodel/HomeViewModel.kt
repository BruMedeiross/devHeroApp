package com.brunadev.devheroapp.login.viewmodel

import androidx.lifecycle.ViewModel
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.DevHeroImpl

class HomeViewModel (private val repository: DevHeroRepository = DevHeroImpl()): ViewModel(){
}
package com.brunadev.devheroapp.login.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse


class LoginViewModel(private val repository: DevHeroRepository = DevHeroImpl()) :
    ViewModel() {

    var email = ObservableField("")
    var password = ObservableField("")

    private val liveData = MutableLiveData<UserResponse?>()

    fun doLogin(): LiveData<UserResponse?> {
        val data = if (validate()) {
            repository.loginUser(
                User(
                    email = email.get()?.trim(),
                    password = password.get()?.trim()
                )
            )
        } else {
            liveData.postValue(null)
            return liveData
        }
        return data
    }


    private fun validate(): Boolean {
        return if (email.get().toString().isEmpty()
            || !email.get().toString().contains("@")
            || password.get().toString().isEmpty()
            || password.get().toString().length < 5
        ) {
            if (password.get().toString().isEmpty() || (password.get().toString().length < 5)) {
            }

            if (email.get().toString().isEmpty() || email.get().toString().contains("@")) {
            }
            false

        } else {
            true
        }
    }


}



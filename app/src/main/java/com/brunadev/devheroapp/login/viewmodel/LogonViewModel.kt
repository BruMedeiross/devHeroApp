package com.brunadev.devheroapp.login.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse
import java.util.*


class LogonViewModel(private val repository: DevHeroRepository = DevHeroImpl()) :
    ViewModel() {

    var email = ObservableField("")
    var userName = ObservableField("")
    var password = ObservableField("")
    var confirmPass = ObservableField("")

    private val liveData = MutableLiveData<UserResponse?>()

    fun newUserAccount(): LiveData<UserResponse?> {
        val data = if (validate()) {
            repository.newUser(
                User(
                    id = UUID.randomUUID().toString(),
                    name = userName.get(),
                    email = email.get(),
                    password = password.get(),
                    passwordConfirm = confirmPass.get()
                )
            )
        } else {
            liveData.postValue(null)
            return liveData
        }
        return data
    }

    fun validate(): Boolean {
        return if (email.get().toString().isEmpty()
            || !email.get().toString().contains("@")
            || password.get().toString().isEmpty()
            || password.get().toString().length < 5
            || confirmPass.get().toString() != password.get().toString()
            || userName.get().toString().length < 5
            || confirmPass.get().toString().isEmpty()
            || userName.get().toString().isEmpty()
        ) {
            if (email.get().toString().isEmpty()
                || !email.get().toString().contains("@")
                || password.get().toString().isEmpty()
                || password.get().toString().length < 5
                || confirmPass.get().toString() != password.get().toString()
                || userName.get().toString().length < 5
                || confirmPass.get().toString().isEmpty()
                || userName.get().toString().isEmpty()
            ) {
            }
            false
        } else {
            true
        }
    }
}

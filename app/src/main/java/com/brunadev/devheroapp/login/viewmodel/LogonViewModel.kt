package com.brunadev.devheroapp.login.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.*
import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.data.model.NewUser
import com.brunadev.devheroapp.login.data.model.NewUserResponser


class LogonViewModel(private val repository: DevHeroRepository = DevHeroImpl()) :
    ViewModel() {

    var email = ObservableField("")
    var userName = ObservableField("")
    var password = ObservableField("")
    var confirmPass = ObservableField("")

    private val _newUserState = MediatorLiveData<NewUserResponser?>()
    val newUserState: LiveData<NewUserResponser?> get() = _newUserState

    private val _formState = MutableLiveData<Boolean>()
    val formState: LiveData<Boolean> get() = _formState

    fun newUserAccount() {
        if (validate()) {
            _formState.postValue(true)
        } else {
            _formState.postValue(false)
        }
    }

    fun createNewUserAccount() {
        val liveDataRequest = repository.newUser(
            NewUser(
                username = userName.get()?.trim(),
                email = email.get()?.trim(),
                password = password.get()?.trim(),
            )
        )
        _newUserState.addSource(liveDataRequest) { userResponse ->
            _newUserState.postValue(userResponse)
        }
    }

    private fun validate(): Boolean {
        return if (!(!email.get().toString().isEmpty() && email.get().toString().contains("@") && !password.get().toString().isEmpty() && password.get().toString().length >= 5 && confirmPass.get().toString() == password.get().toString() && userName.get().toString().length >= 5 && !confirmPass.get().toString().isEmpty() && !userName.get().toString().isEmpty())
        ) {
            if (!(!email.get().toString().isEmpty() && email.get().toString().contains("@") && !password.get().toString().isEmpty() && password.get().toString().length >= 5 && confirmPass.get().toString() == password.get().toString() && userName.get().toString().length >= 5 && !confirmPass.get().toString().isEmpty() && !userName.get().toString().isEmpty())
            ) { }
            false
        } else {
            true
        }
    }
}

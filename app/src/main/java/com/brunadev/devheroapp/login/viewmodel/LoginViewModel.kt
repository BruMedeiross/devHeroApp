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

    private val _userState = MediatorLiveData<UserResponse?>()
    val userState: LiveData<UserResponse?> get() = _userState

    private val _formState = MutableLiveData<Boolean>()
    val formState: LiveData<Boolean> get() = _formState

    // evento -> input (não há retorno na funcao, visto que, o dado que será alterado
    // é o _formState
    fun doLogin() {
        // 1 validar
        if (validate()) {
            _formState.postValue(true)
        } else {
            _formState.postValue(false)
        }
    }

    fun request() {
        val liveDataRequest = repository.loginUser(
            User(email = email.get()?.trim(), password = password.get()?.trim())
        )
        _userState.addSource(liveDataRequest) { userResponse ->
            _userState.postValue(userResponse)
        }
    }

    private fun validate(): Boolean { return if (email.get().toString().isEmpty()
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
// TODO: Adicionar o liveData para 2 cases: Formulario e Server - feito

/*
   // Sendo que, a resposta do server, deverá ser intermediada pelo MediatorLiveData,
   no qual irá capturar a respostas do LiveData presente no Repository

   private val _userState = MediatorLiveData<UserResponse?>()
   val userState: LiveData<UserResponse?> get() = _userState

   private val _formState = MutableLiveData<Boolean>()
   val formState: LiveData<Boolean> get() = _formState

   // evento -> input (não há retorno na funcao, visto que, o dado que será alterado
   // é o _formState
   fun doLogin() {
      // 1 validar
       if (validate()) {
           _formState.postValue(true)
       } else {
           _formState.postValue(false)
       }
   }

   fun request() {
       val liveDataRepo = repository.loginUser(
           User(email = email.get()?.trim(), password = password.get()?.trim())
       )
       // Aqui o liveData do repository é adicionado como fonte de dados (source)
       // para o MediatorLiveData. Uma vez que o dado é alterado (postValue no repository)
       // o UserResponse é capturado dentro do bloco do Mediator.
       // Dessa forma, ao expor o Mediator para a View, ela poderá observar a mudança de estado
       // que ocorreu no Repository, graças ao Mediator.
       _userState.addSource(liveDataRepo) { userResponse ->
          _userState.postValue(userResponse)
       }
   }
   */


//ANTES
//    fun doLogin(): LiveData<UserResponse?> {
//        val data = if (validate()) {
//            repository.loginUser(
//                User(
//                    email = email.get()?.trim(),
//                    password = password.get()?.trim()
//                )
//            )
//        } else {
//            liveData.postValue(null)
//            return liveData
//        }
//        return data
//    }

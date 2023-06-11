package com.brunadev.devheroapp.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.data.model.AddRequest
import com.brunadev.devheroapp.login.data.model.AddResponse
import kotlinx.android.synthetic.main.dialog_create.view.*

class DialogViewModel(private val repository: DevHeroRepository = DevHeroImpl()) : ViewModel() {

    private val _newProjectState = MediatorLiveData<AddResponse?>()
    val newProjectState: LiveData<AddResponse?> = _newProjectState

    private val _formState = MutableLiveData<Boolean>()
    val formState: LiveData<Boolean> = _formState

    fun saveData(addRequest: AddRequest) {
        if (validateForm(addRequest)) {
            _formState.postValue(true)
        } else {
            _formState.postValue(false)
        }
    }

    fun request(addRequest: AddRequest) {
        val liveDataRequest = repository.addProjects(addRequest)

        _newProjectState.addSource(liveDataRequest) { apiResponse ->
            _newProjectState.postValue(apiResponse)
        }
    }

    private fun validateForm(addRequest: AddRequest): Boolean {
        return if (addRequest.desc.isNullOrBlank()
            || addRequest.title.isNullOrBlank()
        ) {
            if (addRequest.desc.isNullOrBlank()
                || addRequest.title.isNullOrBlank()) { }
            false
        } else {
            true
        }
    }
}
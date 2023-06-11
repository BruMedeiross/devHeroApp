package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunadev.devheroapp.login.commom.FakeDataBase
import com.brunadev.devheroapp.login.data.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class DevHeroImpl : DevHeroRepository {

    private val remoteDataSource = RemoteDataSource()
    private val fakeDataSource = FakeDataBase()
    private val compositeDisposable = CompositeDisposable()

    override fun loginUser(user: User): LiveData<UserResponse?> {

        val data = MutableLiveData<UserResponse?>()

        val disposableObserver = remoteDataSource.loginRequest(user)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<UserResponse>() {
                override fun onComplete() {

                }

                override fun onNext(response: UserResponse) {
                    if (response.token?.isNotEmpty() == true) {
                        data.postValue(response)
                        BaseApplication.saveToken(response.token.toString())
                    } else {
                        data.postValue(null)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    data.postValue(null)
                }
            })
        disposableObserver?.let { compositeDisposable.add(it) }
        return data
    }

    override fun newUser(user: NewUser): LiveData<NewUserResponser?> {
        val data = MutableLiveData<NewUserResponser?>()

        val disposableObserver = remoteDataSource.logonRequest(user)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<NewUserResponser>() {
                override fun onComplete() {
                }

                override fun onNext(response: NewUserResponser) {
                    if (response.email != null) {
                        data.postValue(response)
                    } else {
                        data.postValue(null)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    e.message.toString()
                    data.postValue(null)
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }
        return data
    }

    override fun getProjects(): LiveData<List<Projects>> {
        val data = MutableLiveData<List<Projects>>()

        fakeDataSource.getAll {
            if(it != null)
                data.postValue(it.list)
        }
        return data
    }

    override fun getProjectsAPI(): MutableLiveData<List<Projects>?> {
        val data = MutableLiveData<List<Projects>?>()

        val disposableObserver = remoteDataSource.listProjects()
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<List<Projects>>() {
                override fun onComplete() {
                }

                override fun onNext(response: List<Projects>) {
                    if (response != null) {
                        data.postValue(response)
                    } else {
                        data.postValue(null)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    e.message.toString()
                    data.postValue(null)
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }
        return data

    }


    override fun addProjects(addProject: AddRequest): LiveData<AddResponse?> {
        val data = MutableLiveData<AddResponse?>()

        val disposableObserver = remoteDataSource.addProjects(addProject)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<AddResponse>() {
                override fun onComplete() {
                }

                override fun onNext(response: AddResponse) {
                    if (response != null) {
                        data.postValue(response)
                    } else {
                        data.postValue(null)
                    }
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    e.message.toString()
                    data.postValue(null)
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }
        return data
    }


}











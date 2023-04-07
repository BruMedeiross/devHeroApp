package com.brunadev.devheroapp.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brunadev.devheroapp.login.data.model.RemoteDataSource
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.data.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.*

class DevHeroImpl : DevHeroRepository {

    private val remoteDataSource = RemoteDataSource()
    private val compositeDisposable = CompositeDisposable()

    private var userA = User(id = "1", name = "UserA", email = "b@", password = "123456", passwordConfirm = "123456")

    override fun loginUser(user: User): LiveData<UserResponse?> {

        val data = MutableLiveData<UserResponse?>()

        if (userA.email.equals(user.email) && userA.password.equals(user.password)) {
            val disposableObserver = remoteDataSource.loginRequest(userA)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<UserResponse>() {
                    override fun onComplete() {

                    }

                    override fun onNext(response: UserResponse) {
                        if (response.args != null) {
                            data.postValue(response)
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
        } else {
            data.postValue(null)
        }
        return data
    }

    override fun newUser(user: User): LiveData<UserResponse?> {
        user.id =  UUID.randomUUID().toString().trim()

        val data = MutableLiveData<UserResponse?>()

        val disposableObserver = remoteDataSource.logonRequest(user)
            .observeOn(Schedulers.io())
            .subscribeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableObserver<UserResponse>() {
                override fun onComplete() {
                }

                override fun onNext(response: UserResponse) {
                    if (response.args != null) {
                        data.postValue(response)
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

//    override fun getProjects(): MutableLiveData<List<Projects?>> {
//        val data = MutableLiveData<List<Projects?>?>()
//
//        val disposableObserver = remoteDataSource.listProjects()
//            .observeOn(Schedulers.io())
//            .subscribeOn(AndroidSchedulers.mainThread())
//            .subscribeWith(object : DisposableObserver<List<Projects?>>() {
//                override fun onComplete() {
//                }
//
//                override fun onNext(response: List<Projects?>) {
//                    if (response.isNotEmpty()) {
//                        data.postValue(response)
//                    } else {
//                        data.postValue(null)
//                    }
//                }
//
//                @SuppressLint("NullSafeMutableLiveData")
//                override fun onError(e: Throwable) {
//                    e.printStackTrace()
//                    data.postValue(null)
//                }
//            })
//
//        disposableObserver?.let { compositeDisposable.add(it) }
//        return data
//    }
}







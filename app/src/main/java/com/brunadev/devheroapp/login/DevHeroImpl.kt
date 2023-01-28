package com.brunadev.devheroapp.login


import android.annotation.SuppressLint
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

    private var userA = User(UUID.randomUUID().toString(), "UserA", "b@", "12345", "123456")

    override fun loginUser(user: User?): LiveData<UserResponse?> {

        val data = MutableLiveData<UserResponse?>()

        if (userA.email.equals(user?.email) && userA.password.equals(user?.password)) {
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

    override fun newUser(user: User?): LiveData<UserResponse?> {

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

                @SuppressLint("NullSafeMutableLiveData")
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    data.postValue(null)
                }
            })

        disposableObserver?.let { compositeDisposable.add(it) }
        return data
    }
}







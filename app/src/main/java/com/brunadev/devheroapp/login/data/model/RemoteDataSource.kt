package com.brunadev.devheroapp.login.data.model

import com.brunadev.devheroapp.login.BaseApplication
import com.brunadev.devheroapp.login.commom.HTTPClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RemoteDataSource {

    fun loginRequest(loginUser: User): Observable<UserResponse> = HTTPClient.devHeroApi
        .requestLogin(
            user = loginUser
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun logonRequest(registerUser: NewUser): Observable<NewUserResponser> = HTTPClient.devHeroApi
        .requestUser(
            user = registerUser
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun addProjects(addRequest: AddRequest): Observable<AddResponse> = HTTPClient.devHeroApi
        .addItem(
            project = addRequest
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun listProjects(): Observable<List<Projects>> = HTTPClient.devHeroApi
        .getProjects()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}
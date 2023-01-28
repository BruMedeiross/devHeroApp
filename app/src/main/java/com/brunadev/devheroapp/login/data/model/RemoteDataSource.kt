package com.brunadev.devheroapp.login.data.model

import com.brunadev.devheroapp.login.commom.HTTPClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class RemoteDataSource {

    fun loginRequest(user: User?): Observable<UserResponse> = HTTPClient.devHeroApi
        .requestUser(
            id = user?.id,
            email = user?.email,
            name = user?.name,
            pass = user?.password,
            passConfirm = user?.passwordConfirm
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun logonRequest(user: User?): Observable<UserResponse> = HTTPClient.devHeroApi
        .requestUser(
            id = user?.id,
            email = user?.email,
            name = user?.name,
            pass = user?.password,
            passConfirm = user?.passwordConfirm
        )
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


}
package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.data.model.*
import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @POST("auth/users/")
    fun requestUser(
        @Body user: NewUser
    ): Observable<NewUserResponser>

    @POST("auth/token/login")
    fun requestLogin(
        @Body user: User
    ): Observable<UserResponse>

    @POST("items/")
    fun addItem(
        @Body project: AddRequest
    ): Observable<AddResponse>

    @GET("items/")
    fun getProjects(
    ): Observable<List<Projects>>

}
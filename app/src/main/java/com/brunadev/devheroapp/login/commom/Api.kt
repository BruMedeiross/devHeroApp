package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.data.model.LoginResponse
import com.brunadev.devheroapp.login.data.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @GET("/get")
    fun loginRequest(
        @Query("id") id: String?,
        @Query("email") email: String?,
        @Query("name") name: String?,
        @Query("pass") pass: String?,
        @Query("passConfirm") passConfirm: String?
    ): Observable<LoginResponse>

    @POST("/post")
    fun requestUser(
        @Query("id") id: String?,
        @Query("email") email: String?,
        @Query("name") name: String?,
        @Query("pass") pass: String?,
        @Query("passConfirm") passConfirm: String?
    ): Observable<UserResponse>

    @POST("/post")
    fun logoutUser(): Observable<LoginResponse>
}
package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.data.model.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface Api {

    @GET("/image")
    fun listprojects(): Observable<List<Projects?>>

    @POST("/post")
    fun requestUser(
        @Query("id") id: String?,
        @Query("email") email: String?,
        @Query("name") name: String?,
        @Query("pass") pass: String?,
        @Query("passConfirm") passConfirm: String?
    ): Observable<UserResponse>
}
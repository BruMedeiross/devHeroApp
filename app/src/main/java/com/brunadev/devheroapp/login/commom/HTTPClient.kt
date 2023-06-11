package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.BaseApplication
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object HTTPClient {

    private const val BASE_URL = "http://192.168.15.6:8000/app/"

    var gson = GsonBuilder()
        .setLenient()
        .create()

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val original = chain.request()

            if(BaseApplication.getToken().isNullOrBlank())
                return@addInterceptor chain.proceed(original)

            val requestBuilder = original.newBuilder()
                .addHeader("Authorization", "Token ${BaseApplication.getToken()}")
                .method(original.method(), original.body())
                .build()

            chain.proceed(requestBuilder)
    }.build()

    val devHeroApi: Api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson)) //conversor de objetos
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java) // inicializa a api
}
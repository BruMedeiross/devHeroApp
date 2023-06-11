package com.brunadev.devheroapp.login

import android.app.Application
import android.content.Context
import com.brunadev.devheroapp.login.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

private const val KEY_PREFERENCES = "app_pref"
private const val KEY_TOKEN = "app_token"
class BaseApplication: Application(){

    companion object{
        private lateinit var instance: BaseApplication

        private val preferences by lazy {
            instance.getSharedPreferences(KEY_PREFERENCES, Context.MODE_PRIVATE)
        }


        fun saveToken(token: String?){
            preferences.edit()
                .putString(KEY_TOKEN, token)
                .apply()
        }

        fun getToken() =
            preferences.getString(KEY_TOKEN, null)

    }


    override fun onCreate() {
        super.onCreate()

        instance = this@BaseApplication

        startKoin{
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}
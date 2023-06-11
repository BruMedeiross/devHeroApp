package com.brunadev.devheroapp.login.di

import com.brunadev.devheroapp.login.DevHeroImpl
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.commom.Api
import com.brunadev.devheroapp.login.viewmodel.DialogViewModel
import com.brunadev.devheroapp.login.viewmodel.HomeViewModel
import com.brunadev.devheroapp.login.viewmodel.LoginViewModel
import com.brunadev.devheroapp.login.viewmodel.LogonViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

object AppModule {

    val appModule = module {
        single { provideApi(get()) }
        single<DevHeroRepository> { DevHeroImpl() }

        viewModel { LoginViewModel(get()) }
        viewModel { LogonViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { DialogViewModel(get()) }
    }

    private fun provideApi(retrofit: Retrofit): Api =
        retrofit.create(Api::class.java)

}

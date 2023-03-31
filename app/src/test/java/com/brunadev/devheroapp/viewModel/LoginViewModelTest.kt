package com.brunadev.devheroapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LoginViewModel
import com.nhaarman.mockito_kotlin.*
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LoginViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var viewModel: LoginViewModel

    private val userResponse = UserResponse(args = User("1", "bruna", "b@", "123456", "123456"))

    @Mock
    lateinit var repository: DevHeroRepository

    @Before
    fun setup() {
        viewModel = LoginViewModel(repository)
    }

    @Test
    fun given_valid_login_user(){

        viewModel.email.set("b@")
        viewModel.password.set("123456")

        viewModel.doLogin()

        assertEquals(true, viewModel.formState.value)
    }

    @Test
    fun given_invalid_login_user(){

        viewModel.email.set("mail")
        viewModel.password.set("123456")

        viewModel.doLogin()

        assertEquals(false, viewModel.formState.value)
    }


    @Test
    fun given_request_login_user_then_sucess(){

        viewModel.email.set("b@")
        viewModel.password.set("123456")

        val liveData = MediatorLiveData<UserResponse?>()
        liveData.value = userResponse

        val user = User(email = "b@", password = "123456")
        doReturn(liveData).`when`(repository).loginUser(user)

        viewModel.request()

        assertEquals(userResponse, liveData.value)
    }

    @Test
    fun given_request_login_user_then_fail(){

        viewModel.email.set("b@")
        viewModel.password.set("111111")

        val liveData = MediatorLiveData<UserResponse?>()
        liveData.value = null

        val user = User(email = "b@", password = "111111")
        doReturn(liveData).`when`(repository).loginUser(user)

        viewModel.request()
        viewModel.userState.observeForever {  }

        assertEquals(null, viewModel.userState.value)
    }
}

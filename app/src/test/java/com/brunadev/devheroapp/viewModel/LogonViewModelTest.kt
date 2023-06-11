package com.brunadev.devheroapp.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MediatorLiveData
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.data.model.NewUser
import com.brunadev.devheroapp.login.data.model.NewUserResponser
import com.brunadev.devheroapp.login.data.model.User
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LogonViewModel
import com.nhaarman.mockito_kotlin.doReturn
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LogonViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private lateinit var viewModel: LogonViewModel

    private val userResponse = NewUserResponser("b@", "123456")

    @Mock
    lateinit var repository: DevHeroRepository

    @Before
    fun setup() {
        viewModel = LogonViewModel(repository)
    }

    @Test
    fun given_valid_data_logon_user() {

        viewModel.email.set("b@")
        viewModel.userName.set("bruna")
        viewModel.password.set("123456")
        viewModel.confirmPass.set("123456")

        viewModel.newUserAccount()

        assertEquals(true, viewModel.formState.value)
    }

    @Test
    fun given_invalid_data_logon_user() {

        viewModel.email.set("")
        viewModel.userName.set("bruna")
        viewModel.password.set("123456")
        viewModel.confirmPass.set("123456")

        viewModel.newUserAccount()

        assertEquals(false, viewModel.formState.value)
    }

    @Test
    fun given_new_user_account_response_success() {

        viewModel.email.set("b@")
        viewModel.userName.set("bruna")
        viewModel.password.set("123456")
        viewModel.confirmPass.set("123456")

        val liveDataNewAccount = MediatorLiveData<NewUserResponser?>()
        liveDataNewAccount.value = userResponse

        val newUser = NewUser(username ="bruna", email="b@", password = "123456")

        doReturn(liveDataNewAccount).`when`(repository).newUser(newUser)

        viewModel.createNewUserAccount()
        viewModel.newUserState.observeForever {  }

        assertEquals(userResponse, viewModel.newUserState.value)
    }


    @Test
    fun given_new_user_account_then_fail() {

        viewModel.email.set("b@")
        viewModel.userName.set("bruna")
        viewModel.password.set("123456")
        viewModel.confirmPass.set("123456")

        val liveDataNewAccount = MediatorLiveData<NewUserResponser?>()
        liveDataNewAccount.value = null

        val newUser = NewUser(username ="bruna", email="b@", password = "123456")

        doReturn(liveDataNewAccount).`when`(repository).newUser(newUser)

        viewModel.createNewUserAccount()
        viewModel.newUserState.observeForever {  }

        assertEquals(null, viewModel.newUserState.value)
    }
}
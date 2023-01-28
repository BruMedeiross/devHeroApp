package com.brunadev.devheroapp.login.view

import kotlinx.android.synthetic.main.activity_main.emailText
import kotlinx.android.synthetic.main.activity_main.passwordText
import kotlinx.android.synthetic.main.activity_main.view.*
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityMainBinding
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LoginViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.label_logon
import kotlinx.android.synthetic.main.activity_main.label_notify
import kotlinx.android.synthetic.main.activity_main.welcome


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModelLogin = viewModel

        btn_acess.setOnClickListener {
           // it.hideKeyboard()
            setObservers()
        }

        label_logon.setOnClickListener {
            goToLogonScreen()
        }
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        with(viewModel) {
            doLogin().observe(this@MainActivity) { user ->
                if (user != null) {
                    validLogin(user)
                    goToHomeScreen(user)
                } else {
                    invalidLogin()
                }
            }
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(emailText.windowToken, 0)
        imm.hideSoftInputFromWindow(passwordText.windowToken, 0)
    }

    private fun validLogin(user: UserResponse?) {
        welcome.text = getString(R.string.text_welcome_loguin, user?.args?.name)
        label_notify.text = getString(R.string.label_notice_home_user, user?.args?.id?.length.toString())
        clearFindViewByIdCache()
        emailText.error = null
        passwordText.error = null
    }

    private fun goToHomeScreen(user: UserResponse?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("idUser", user?.args?.id)
        intent.putExtra("nameUser", user?.args?.name)

        startActivity(intent)
        clearFindViewByIdCache()
    }

    private fun goToLogonScreen() {
        val intent = Intent(this, LogonActivity::class.java)
        startActivity(intent)
    }

    private fun invalidLogin() {
        passwordText.requestFocus()
        emailText.requestFocus()
        emailText.error = getString(R.string.error_login)
        passwordText.error = getString(R.string.error_login)
    }
}


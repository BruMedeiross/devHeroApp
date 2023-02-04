package com.brunadev.devheroapp.login.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityMainBinding
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LoginViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var viewModelLogin: LoginViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        viewModelLogin = ViewModelProvider(this)[LoginViewModel::class.java]
        binding.viewModelLogin = viewModelLogin

        setObservers()

        binding.btnAcess.setOnClickListener {
            hideKeyboard()
            viewModelLogin.doLogin()
        }

        binding.labelLogon.setOnClickListener {
            goToLogonScreen()
        }
    }

    private fun setObservers() {
        with(viewModelLogin) {
            formState.observe(this@MainActivity) { valid ->
                if (!valid) {
                    invalidLogin()
                } else {
                    request()
                }
            }

            userState.observe(this@MainActivity) { user ->
                if (user != null) {
                    validLogin(user)
                    goToHomeScreen(user)
                } else {
                    invalidLogin()
                }
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.emailText.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.passwordText.windowToken, 0)
    }

    private fun validLogin(user: UserResponse?) {
        binding.welcome.text = getString(R.string.text_welcome_loguin, user?.args?.name)
        binding.labelNotify.text =
            getString(R.string.label_notice_home_user, user?.args?.id?.length.toString())
        binding.emailText.error = null
        binding.passwordText.error = null
    }

    private fun goToHomeScreen(user: UserResponse?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("idUser", user?.args?.id)
        intent.putExtra("nameUser", user?.args?.name)
        startActivity(intent)
    }

    private fun goToLogonScreen() {
        val intent = Intent(this, LogonActivity::class.java)
        startActivity(intent)
    }

    private fun invalidLogin() {
        binding.passwordText.requestFocus()
        binding.emailText.requestFocus()
        binding.emailText.error = getString(R.string.error_login)
        binding.passwordText.error = getString(R.string.error_login)
    }
}

// TODO: Adicionar no onCreate para evitar o "reload" dos observers- feito
// TODO: Adicionar observador para validacao de formulario E respostas do servidor = feito
// TODO: Remover extension, transformando-o em função da classe - feito
// TODO: Adicionar o binding para evitar nullpointer do kotlin synthetic - feito


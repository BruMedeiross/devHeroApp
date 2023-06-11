package com.brunadev.devheroapp.login.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityMainBinding
import com.brunadev.devheroapp.login.BaseApplication
import com.brunadev.devheroapp.login.commom.NetworkChecker
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LoginViewModel
import com.github.razir.progressbutton.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private val viewModelLogin: LoginViewModel by viewModel()
    private lateinit var binding: ActivityMainBinding
    private val networkChecker by lazy {
        NetworkChecker(getSystemService(this@LoginActivity, ConnectivityManager::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        this@LoginActivity.bindProgressButton(binding.btnAcess)
        setObservers()

        binding.viewModelLogin = viewModelLogin

        binding.btnAcess.setOnClickListener {
            networkChecker.performActionIfConnectet {
                showProgress()
                hideKeyboard()
                viewModelLogin.doLogin()
            }
        }

        binding.labelLogon.setOnClickListener {
            goToLogonScreen()
        }

        binding.btnAcessGoogle.setOnClickListener {
            acessGoogle()
        }

    }

    override fun onStart() {
        super.onStart()
        BaseApplication.saveToken(null)
    }

    private fun acessGoogle() {
        Thread{
            runOnUiThread {
                Thread.sleep(1000)
                binding.btnAcessGoogle.showProgress{
                    buttonTextRes = R.string.loading
                    progressColor = Color.BLACK
                }
            }

        }.start()
    }

    private fun showProgress() {
        binding.btnAcess.setBackgroundColor(getColor(R.color.blue))

        binding.btnAcess.attachTextChangeAnimator {
            fadeOutMills = 300
            fadeInMills = 400
            useCurrentTextColor = false

            textColor = Color.WHITE
            textColorRes = R.color.white
        }

        binding.btnAcess.showProgress{
            buttonTextRes = R.string.loading
            progressColor = Color.WHITE
        }
    }

    private fun hideProgress() {
        binding.btnAcess.hideProgress(R.string.acess)
        binding.btnAcessGoogle.hideProgress(R.string.acess_google)
    }

    private fun setObservers() {
        with(viewModelLogin) {
            formState.observe(this@LoginActivity) { valid ->
                if (!valid) {
                    invalidLogin()
                    hideProgress()
                } else {
                    request()
                }
            }

            userState.observe(this@LoginActivity) { user ->
                if (user != null) {
                    validLogin(user)
                    goToHomeScreen(user)
                } else {
                    invalidLogin()
                }
                hideProgress()
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.emailText.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.passwordText.windowToken, 0)
    }

    private fun validLogin(user: UserResponse?) {
        binding.welcome.text = getString(R.string.text_welcome_loguin, "DEV.")
        binding.emailText.error = null
        binding.passwordText.error = null
    }

    private fun goToHomeScreen(user: UserResponse?) {
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra("idUser", user?.token?.length)
        intent.putExtra("nameUser", user?.email.toString())
        startActivity(intent)
    }

    private fun goToLogonScreen() {
        val intent = Intent(this, LogonActivity::class.java)
        startActivity(intent)
        BaseApplication.saveToken(null)
    }

    private fun invalidLogin() {
        binding.passwordText.requestFocus()
        binding.emailText.requestFocus()
        binding.emailText.error = getString(R.string.error_login)
        binding.passwordText.error = getString(R.string.error_login)
    }

    override fun onDestroy() {
        super.onDestroy()
        BaseApplication.saveToken(null)
    }
}

// TODO: Adicionar no onCreate para evitar o "reload" dos observers- feito
// TODO: Adicionar observador para validacao de formulario E respostas do servidor = feito
// TODO: Remover extension, transformando-o em função da classe - feito
// TODO: Adicionar o binding para evitar nullpointer do kotlin synthetic - feito


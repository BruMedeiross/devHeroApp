package com.brunadev.devheroapp.login.view

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.ViewModelProvider
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityLogonBinding
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LogonViewModel
import com.github.razir.progressbutton.attachTextChangeAnimator
import com.github.razir.progressbutton.hideProgress
import com.github.razir.progressbutton.showProgress
import kotlinx.android.synthetic.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class LogonActivity : AppCompatActivity() {

    private val viewModelLogon: LogonViewModel by viewModel()
    private lateinit var binding: ActivityLogonBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLogonBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.viewModelLogon = viewModelLogon

        setObservers()

        binding.btnAcessLogon.setOnClickListener {
            showProgress()
            hideKeyboard()
            viewModelLogon.newUserAccount()
        }

        binding.btnLoginLogon.setOnClickListener {
            goToLoginScreen()
        }
    }

    private fun setObservers() {
        with(viewModelLogon) {
            formState.observe(this@LogonActivity) { valid ->
                if (!valid) {
                    invalidNewUser()
                } else {
                    createNewUserAccount()
                }
            }

            newUserState.observe(this@LogonActivity) { newUser ->
                if (newUser != null) {
                    newUserCreated(newUser)
                    hideProgress()
                } else {
                    errorCreateNewAccount()
                }
            }
        }
    }


    private fun hideProgress() {
        binding.btnAcessLogon.hideProgress(R.string.text_account_newuser)
    }

    private fun showProgress() {
        binding.btnAcessLogon.setBackgroundColor(getColor(R.color.blue))

        binding.btnAcessLogon.attachTextChangeAnimator {
            fadeOutMills = 300
            fadeInMills = 400
            useCurrentTextColor = false

            textColor = Color.WHITE
            textColorRes = R.color.white
        }

        binding.btnAcessLogon.showProgress{
            buttonTextRes = R.string.text_account_register
            progressColor = Color.WHITE
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.emailTextLogon.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.passwordTextLogon.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.userNameTextLogon.windowToken, 0)
        imm.hideSoftInputFromWindow(binding.passwordTextConfirmLogon.windowToken, 0)
    }

    private fun newUserCreated(user: UserResponse?) {
        binding.btnAcessLogon.setText(R.string.text_account_newuser)
        binding.btnAcessLogon.setBackgroundColor(getColor(R.color.blue))
        val intent = Intent(
            this,
            HomeActivity::class.java
        )
        intent.putExtra("idUser", user?.args?.id)
        intent.putExtra("nameUser", user?.args?.name)

        startActivity(intent)
    }

    private fun invalidNewUser() {
        binding.passwordTextLogon.requestFocus()
        binding.emailTextLogon.requestFocus()
        binding.emailTextLogon.error = getString(R.string.error_logon)
        binding.passwordTextLogon.error = getString(R.string.error_logon)
        binding.btnAcessLogon.setText(R.string.error_logon)
        binding.btnAcessLogon.setBackgroundColor(getColor(R.color.error))
    }

    private fun errorCreateNewAccount() {
        binding.btnAcessLogon.setText(R.string.error_api)
        binding.btnAcessLogon.setBackgroundColor(getColor(R.color.error))
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        clearFindViewByIdCache()
    }
}




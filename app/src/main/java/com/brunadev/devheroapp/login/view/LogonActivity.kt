package com.brunadev.devheroapp.login.view

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityLogonBinding
import com.brunadev.devheroapp.login.data.model.UserResponse
import com.brunadev.devheroapp.login.viewmodel.LogonViewModel
import kotlinx.android.synthetic.main.activity_logon.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*

class LogonActivity : AppCompatActivity() {
    private lateinit var viewModel: LogonViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityLogonBinding>(this, R.layout.activity_logon)
        viewModel = ViewModelProvider(this)[LogonViewModel::class.java]
        binding.viewModelLogon = viewModel

        btn_acess_logon.setOnClickListener {
            it.hideKeyboard()
            setObservers()
        }
    }

    override fun onStart() {
        super.onStart()
        setObservers()
    }

    private fun setObservers() {
        with(viewModel) {
            newUserAccount().observe(this@LogonActivity) { user ->
                if (user != null) {
                    validNewUser(user)
                } else {
                    invalidNewUser()
                }
            }
        }
    }

    fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(emailText_logon.windowToken, 0)
        imm.hideSoftInputFromWindow(passwordText_logon.windowToken, 0)
        imm.hideSoftInputFromWindow(userNameText_logon.windowToken, 0)
        imm.hideSoftInputFromWindow(passwordText_confirm_logon.windowToken, 0)
    }

    private fun validNewUser(user: UserResponse?) {
        btn_acess_logon.setText(R.string.text_account_newuser)
        btn_acess_logon.setBackgroundColor(getColor(R.color.pass_blue))
        val intent = Intent(
            this,
            HomeActivity::class.java
        )
        intent.putExtra("idUser", user?.args?.id)
        intent.putExtra("nameUser", user?.args?.name)

        startActivity(intent)
    }

    private fun invalidNewUser() {
        passwordText_logon.requestFocus()
        emailText_logon.requestFocus()
        emailText_logon.error = getString(R.string.error_logon)
        passwordText_logon.error = getString(R.string.error_logon)
        btn_acess_logon.setText(R.string.error_logon)
        btn_acess_logon.setBackgroundColor(getColor(R.color.error))
    }
}




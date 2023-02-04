package com.brunadev.devheroapp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private var idUser: String? = ""
    private var nameUser: String? = ""
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        nameUser = intent.extras?.getString("nameUser")
        idUser = intent.extras?.getString("idUser")

        userData()

        binding.btnLogout.setOnClickListener {
            goToLoginScreen()
        }
    }

    private fun userData(){
       binding.appUser.text = getString(R.string.devhero_name, nameUser)
       binding.labelNotify.text = getString(R.string.label_notice_home_user, idUser?.length.toString())
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

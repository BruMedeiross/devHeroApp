package com.brunadev.devheroapp.login.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.brunadev.devheroapp.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    private var idUser: String? = ""
    private var nameUser: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        nameUser = intent.extras?.getString("nameUser")
        idUser = intent.extras?.getString("idUser")

        userData()
    }

    fun userData(){
       app_user.text = getString(R.string.devhero_name, nameUser)
       label_notify.text = getString(R.string.label_notice_home_user, idUser?.length.toString())
    }
}

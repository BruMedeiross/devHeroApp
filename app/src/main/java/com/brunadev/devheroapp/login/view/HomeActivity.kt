package com.brunadev.devheroapp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityHomeBinding
import com.brunadev.devheroapp.login.data.model.Projects
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.item_projects.*
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var idUser: String? = ""
    private var nameUser: String? = ""
    private lateinit var binding: ActivityHomeBinding
    private lateinit var listAdapter: ListProjectsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val listProjects = arrayListOf(
            Projects(company = "Google", email = "google_company@gmail.com", hours = "Contrato - 50h"),
            Projects(company = "HomeLand", email = "homeland_company@gmail.com", hours = "Contrato - 40h"),
            Projects(company = "JS", email = "js_company@gmail.com", hours = "Contrato - 80h"),
            Projects(company = "HomeLand", email = "homeland_company@gmail.com", hours = "Contrato - 50h"),
            Projects(company = "Google", email = "google_company@gmail.com", hours = "Contrato - 25h"),
            Projects(company = "HomeLand", email = "homeland_company@gmail.com", hours = "Contrato - 150h"),
            Projects(company = "JS", email = "js_company@gmail.com", hours = "Contrato - 80h"),
            Projects(company = "HomeLand", email = "homeland_company@gmail.com", hours = "Contrato - 50h")
        )

        listAdapter = ListProjectsAdapter(listProjects) { company ->
            Toast.makeText(this, "$company", Toast.LENGTH_SHORT).show()
        }

        rv_list.layoutManager = LinearLayoutManager(this)
        rv_list.adapter = listAdapter

        nameUser = intent.extras?.getString("nameUser")
        idUser = intent.extras?.getString("idUser")

        userData()

        binding.btnLogout.setOnClickListener {
            goToLoginScreen()
        }
    }

    private fun userData() {
        binding.appUser.text = getString(R.string.devhero_name, nameUser)
        binding.labelNotify.text =
            getString(R.string.label_notice_home_user, nameUser?.length.toString())
    }

    private fun goToLoginScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}

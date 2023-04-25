package com.brunadev.devheroapp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityHomeBinding
import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class HomeActivity : AppCompatActivity() {

    private var idUser: String? = ""
    private var nameUser: String? = ""
    private lateinit var binding: ActivityHomeBinding
    private val viewModelHome: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setObservers()

        binding.viewModelHome = viewModelHome

        rv_list.layoutManager = LinearLayoutManager(this@HomeActivity)

        nameUser = intent.extras?.getString("nameUser")
        idUser = intent.extras?.getString("idUser")

        userData()

        binding.btnLogout.setOnClickListener {
            goToLoginScreen()
        }
    }

    private fun setObservers() {
        viewModelHome.getAllProjects().observe(this@HomeActivity) { projectList ->
            if (projectList != null) {
                showProjects(projectList)
            }
        }
    }

    private fun showProjects(projectList: List<Projects>) {
        if (projectList != null) {
            rv_list.adapter = ListProjectsAdapter(projectList ) { company ->
                Toast.makeText(this, "$company", Toast.LENGTH_SHORT).show()
            }
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

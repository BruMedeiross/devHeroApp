package com.brunadev.devheroapp.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.brunadev.devheroapp.R
import com.brunadev.devheroapp.databinding.ActivityHomeBinding
import com.brunadev.devheroapp.login.BaseApplication
import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.viewmodel.HomeViewModel
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private var idUser: String? = ""
    private var nameUser: String? = ""
    private var newUserName: String? = ""
    private var newUserEmail: String? = ""
    private lateinit var binding: ActivityHomeBinding
    private val viewModelHome: HomeViewModel by viewModel()
    private var projects: Int = 0


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

        binding.btnLogout.setOnClickListener {
            goToLoginScreen()
        }

        binding.fabDialog.setOnClickListener {
            val dialog =  AddDialog()
            dialog.show(supportFragmentManager, dialog.tag)
        }
    }

    private fun setObservers() {

        viewModelHome.getAllProjects().observe(this@HomeActivity) { projectList ->
            if (projectList?.isNotEmpty() == true && projectList != null) {
                showProjects(projectList)
            }else{
                showExampleProjects()
            }
            projects = projectList?.size ?: 0
            userData()
        }

    }

    private fun showProjects(projectList: List<Projects>) {
        if (projectList != null) {
            rv_list.adapter = ListProjectsAdapter(projectList) { company ->
                Toast.makeText(this, "$company", Toast.LENGTH_SHORT).show()
            }
        }

    }
    private fun showExampleProjects(){
        viewModelHome.getFakeProjects().observe(this@HomeActivity) { projectList ->
            if (projectList != null) {
                showProjects(projectList)
            }
        }
    }


    private fun userData() {
        binding.appUser.text = getString(R.string.devhero_name, nameUser)
        binding.labelNotify.text =
            getString(R.string.label_notice_home_user, projects.toString())
    }

    private fun goToLoginScreen() {
        clearFindViewByIdCache()
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        BaseApplication.saveToken(null)
    }
}

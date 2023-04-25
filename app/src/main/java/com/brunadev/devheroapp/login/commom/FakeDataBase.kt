package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.data.model.GetAllProjects
import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.data.model.User
import java.util.*
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class FakeDataBase {

    companion object {

        private var users: HashSet<User> = hashSetOf()
        private var items: LinkedHashSet<Projects> = linkedSetOf()

        init {

            items.add(
                Projects(
                    company = "Google",
                    email = "google_company@gmail.com",
                    hours = "Contrato - 50h",
                    data = Date().time + 3600,
                )
            )
            items.add(
                Projects(
                    company = "Azure",
                    email = "azure_company@gmail.com",
                    hours = "Contrato - 500h",
                    data = Date().time + 3600
                ),
            )

        }

    }

    fun getAll(response:(GetAllProjects)-> Unit ) {
        Thread.sleep(2000)
        val list = mutableListOf<Projects>()
        items.toCollection(list)
        response(GetAllProjects(list))
    }
}
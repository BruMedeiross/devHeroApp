package com.brunadev.devheroapp.login.commom

import com.brunadev.devheroapp.login.data.model.GetAllProjects
import com.brunadev.devheroapp.login.data.model.Projects
import com.brunadev.devheroapp.login.data.model.NewUser
import kotlin.collections.HashSet
import kotlin.collections.LinkedHashSet

class FakeDataBase {

    companion object {

        private var users: HashSet<NewUser> = hashSetOf()
        private var items: LinkedHashSet<Projects> = linkedSetOf()

        init {

            users.add(
                NewUser(
                    username = "UserA",
                    email = "b@",
                    password = "123456",
                )
            )

            items.add(
                Projects(
                    title = "Google",
                    desc = "google_company@gmail.com",
                    priority = 1,
                    data = "25/05/2023",
                )
            )

        }

    }

    fun getAll(response: (GetAllProjects) -> Unit) {
        Thread.sleep(2000)
        val list = mutableListOf<Projects>()
        items.toCollection(list)
        response(GetAllProjects(list))
    }
}
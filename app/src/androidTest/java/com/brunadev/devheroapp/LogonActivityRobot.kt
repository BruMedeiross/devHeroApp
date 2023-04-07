package com.brunadev.devheroapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.view.LogonActivity

fun LogonActivityTest.withLogonActivity(
    func: LogonActivityRobot.() -> Unit
) = LogonActivityRobot().apply(func)


class LogonActivityRobot {

    private lateinit var repository: DevHeroRepository

    fun launchEmptyActivity(){
        val intent = Intent(ApplicationProvider.getApplicationContext(), LogonActivity::class.java)
        ActivityScenario.launch<LogonActivity>(intent)
    }

    fun clickBtnLogon() = R.id.btn_acess_logon.click()

    fun clickFields() = R.id.userNameText_logon.hasText("")

    fun scrollLayout() = R.id.layout_logon.fastSwipeUp()

    infix fun actions (func: LogonActivityRobot.() -> Unit) = this.apply(func)

    infix fun verify(func: LogonActivityResult.() -> Unit) = LogonActivityResult().apply(func)
}

class LogonActivityResult{

     fun  checkIsDisplayed(){
         R.id.btn_acess_logon.isDisplayed()
         R.id.layout_logon.isDisplayed()
         R.id.btn_login_logon.isDisplayed()
         R.id.btn_login_logon.isEnabled()
     }

    fun checkAppName(){
        "DevHero".isTextDisplayed()
    }

    fun checkTitleCreateAcoount(){
        "Criar sua conta".isTextDisplayed()
    }

    fun checkTitleError(){
        "Todos os campos precisam estar preenchidos".isTextDisplayed()
    }
}
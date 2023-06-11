package com.brunadev.devheroapp

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import com.brunadev.devheroapp.login.DevHeroRepository
import com.brunadev.devheroapp.login.view.LoginActivity

fun LoginActivityTest.withMainActivity(
    func: MainActivityRobot.() -> Unit
) = MainActivityRobot().apply(func)


class MainActivityRobot {

    private lateinit var repository: DevHeroRepository

    fun launchEmptyActivity(){

        val intent = Intent(ApplicationProvider.getApplicationContext(), LoginActivity::class.java)
        ActivityScenario.launch<LoginActivity>(intent)
    }

    fun clickBtnLogin() = R.id.btn_acess.click()

    fun clickBtnGoogle() = R.id.btn_acess_google.click()

    infix fun actions (func: MainActivityRobot.() -> Unit) = this.apply(func)

    infix fun verify(func: MainActivityResult.() -> Unit) = MainActivityResult().apply(func)
}

class MainActivityResult{

     fun  checkIsDisplayed(){
         R.id.btn_acess.isDisplayed()
         R.id.layout_login.isDisplayed()
         R.id.btn_acess_google.isDisplayed()
         R.id.emailText.isDisplayed()
     }

    fun checkAppName(){
        "DevHero".isTextDisplayed()
    }
}
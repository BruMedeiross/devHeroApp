package com.brunadev.devheroapp

import org.junit.Test

class LoginActivityTest {

    @Test
    fun givenHomeActivityWhenClickButtonThenCheckDisplayedItens() {
        withMainActivity {
            launchEmptyActivity()
        } actions {
            clickBtnLogin()
            clickBtnGoogle()
        } verify {
            checkIsDisplayed()
            checkAppName()
        }
    }
}
package com.brunadev.devheroapp

import org.junit.Test

class MainActivityTest {

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
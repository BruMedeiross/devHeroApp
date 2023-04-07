package com.brunadev.devheroapp

import org.junit.Test

class LogonActivityTest {

    @Test
    fun givenLogonActivityWhenClickButtonThenCheckDisplayedItens() {
        withLogonActivity {
            launchEmptyActivity()
        } actions {
            scrollLayout()
            clickBtnLogon()
            Thread.sleep(1000)
        } verify {
            checkIsDisplayed()
            checkAppName()
            checkTitleCreateAcoount()
            checkTitleError()
        }
    }

    @Test
    fun givenLogonActivityWhenClickTextFieldThenCheckDisplayedItens() {
        withLogonActivity {
            launchEmptyActivity()
        } actions {
            clickFields()
        }verify {
            checkIsDisplayed()
            Thread.sleep(2000)
        }
    }
}
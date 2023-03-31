package com.brunadev.devheroapp

import org.junit.Test

class HomeActivityTest {

    @Test
    fun givenHomeActivityWhenClickButtonThenCheckDisplayedItens(){
        withHomeActivity {
            launchEmptyActivity()
        } actions {
            clickBtnLogin()
        }verify{
            checkIfIsDisplayed()
            checkAppName()
        }
    }

}
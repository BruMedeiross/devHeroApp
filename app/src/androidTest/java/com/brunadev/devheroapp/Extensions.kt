package com.brunadev.devheroapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import android.view.View
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.GeneralSwipeAction
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Swipe
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.UiController
import androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import org.hamcrest.Matcher
import org.hamcrest.core.AllOf.allOf
import androidx.test.espresso.matcher.RootMatchers
import org.hamcrest.CoreMatchers.not

fun performAction(id: Int, action: ViewAction) = onView(withId(id)).perform(action)

private fun performAction(text: String, action: ViewAction) = onView(withText(text)).perform(action)

fun String.isTextDisplayed(): ViewInteraction = onView(withText(this)).check(matches(isDisplayed()))

fun String.isTextNotDisplayed(): ViewInteraction =
    onView(withText(this)).check(matches(not(isDisplayed())))

fun String.click(): ViewInteraction = performAction(this, ViewActions.click())

fun String.clickAtDialogMessage() {
    onView(withText(this))
        .inRoot(RootMatchers.isDialog())
        .check(matches(isDisplayed()))
        .perform(ViewActions.click())
}


class ScrollRecyclerToBottom : ViewAction {
    override fun getConstraints(): Matcher<View> {
        return allOf<View>(isAssignableFrom(RecyclerView::class.java), isDisplayed())
    }

    override fun getDescription(): String = "Scroll RecyclerView to bottom"

    override fun perform(uiController: UiController?, view: View?) {
        val recyclerView = view as RecyclerView
        val itemCount = recyclerView.adapter?.itemCount
        val position = itemCount?.minus(1) ?: 0
        recyclerView.scrollToPosition(position)
        uiController?.loopMainThreadUntilIdle()
    }
}

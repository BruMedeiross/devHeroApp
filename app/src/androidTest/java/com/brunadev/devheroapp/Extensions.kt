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

fun Int.click(): ViewInteraction = performAction(this, ViewActions.click())

fun Int.clickWithValidatableButtonText(text: String): ViewInteraction =
    onView(withText(text))
        .perform((ViewActions.click()))

fun Int.scrollToBottom(): ViewInteraction = performAction(this, ScrollRecyclerToBottom())

fun Int.clickViewWithText(text: String): ViewInteraction =
    onView(withText(text)).perform((ViewActions.click()))

fun Int.setValidatableEditText(text: String?): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.withText(text)))

fun Int.hasText(text: String): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.withText(text)))

fun Int.replaceText(text: String): ViewInteraction =
    onView(withId(this)).perform(ViewActions.replaceText(text))

fun Int.isEnabled(): ViewInteraction = onView(withId(this)).check(matches(ViewMatchers.isEnabled()))

fun Int.isGone(): ViewInteraction = onView(withId(this))
    .check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.GONE)))

fun Int.isVisible(): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))

fun Int.fastSwipeUp(): ViewInteraction = performAction(
    this,
    GeneralSwipeAction(
        Swipe.FAST,
        GeneralLocation.BOTTOM_CENTER,
        GeneralLocation.BOTTOM_CENTER,
        Press.PINPOINT
    )
)

fun Int.slowSwipeUp(): ViewInteraction = performAction(
    this,
    GeneralSwipeAction(
        Swipe.SLOW,
        GeneralLocation.CENTER,
        GeneralLocation.TOP_CENTER,
        Press.FINGER
    )
)

fun Int.slowSwipeDown(): ViewInteraction = performAction(
    this,
    GeneralSwipeAction(
        Swipe.SLOW,
        GeneralLocation.CENTER,
        GeneralLocation.BOTTOM_CENTER,
        Press.FINGER
    )
)

fun Int.isDisplayed(): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.isDisplayed()))

fun Int.isChecked(): ViewInteraction = onView(withId(this)).check(matches(ViewMatchers.isChecked()))

fun Int.isNotChecked(): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.isNotChecked()))

fun Int.isSelected(): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.isSelected()))

fun Int.isNotSelected(): ViewInteraction =
    onView(withId(this)).check(matches(ViewMatchers.isNotSelected()))

fun Int.scrollTo(): ViewInteraction = performAction(this, ViewActions.scrollTo())

fun Int.scrollAndClick() {
    scrollTo()
    click()
}

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

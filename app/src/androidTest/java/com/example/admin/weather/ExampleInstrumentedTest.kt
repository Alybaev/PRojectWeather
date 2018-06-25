package com.example.admin.weather

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso.closeSoftKeyboard
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.typeText
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import android.support.test.rule.ActivityTestRule
import org.junit.Rule



/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    val mActivityRule = ActivityTestRule(
            MainActivity::class.java)



    @Test
    fun useAppContext() {
        // Context of the app under test.

        onView(withId(R.id.ed))
                .perform(typeText("njn"), closeKeybord());
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.example.admin.weather", appContext.packageName)
    }
}

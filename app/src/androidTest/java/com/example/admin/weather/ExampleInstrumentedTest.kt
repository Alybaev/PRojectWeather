package com.example.admin.weather

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import android.support.test.rule.ActivityTestRule
import org.junit.Rule
import android.support.test.uiautomator.UiSelector
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.espresso.intent.Intents
import android.support.test.espresso.intent.Intents.intended
import android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent
import android.support.test.uiautomator.UiDevice
import android.support.test.espresso.IdlingRegistry
import android.support.test.runner.lifecycle.Stage
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.ActivityLifecycleMonitor
import android.support.test.espresso.IdlingResource
import android.support.test.espresso.IdlingPolicies
import android.text.format.DateUtils
import com.example.admin.weather.ui.main.MainActivity
import com.example.admin.weather.ui.MapsActivity

import java.util.concurrent.TimeUnit


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    public val mActivityRule = ActivityTestRule(
            MapsActivity::class.java)



    @Test
    fun checkViewPagerSwipe() {

        //Click on marker on the map
        val device = UiDevice.getInstance(getInstrumentation())
        val marker = device.findObject(UiSelector().descriptionContains("Bishkek"))
        marker.click()

        //Check is Main activity launched
        Intents.init()
        val mainActivityName = MainActivity::class.java!!.getName()
        IdlingRegistry.getInstance().register(WaitActivityIsResumedIdlingResource(mainActivityName))
        intended(hasComponent(MainActivity::class.java!!.name))

        //Perform swipe Left
        onView(withId(R.id.view_pager)).perform(swipeLeft())

        // Make sure Espresso does not time out
        var waitingTime = DateUtils.SECOND_IN_MILLIS
        IdlingPolicies.setMasterPolicyTimeout(
                waitingTime * 6,  TimeUnit.MILLISECONDS)
        IdlingPolicies.setIdlingResourceTimeout(
                waitingTime * 6, TimeUnit.MILLISECONDS)

        // Now we wait
        val idlingResource = ElapsedTimeIdlingResource(waitingTime)
        IdlingRegistry.getInstance().register(idlingResource)

        onView(withId(R.id.view_pager)).perform(swipeRight())
        IdlingRegistry.getInstance().unregister(idlingResource)

    }
}


private class WaitActivityIsResumedIdlingResource(private val activityToWaitClassName: String) : IdlingResource {

    private val instance: ActivityLifecycleMonitor
    @Volatile private var resourceCallback: IdlingResource.ResourceCallback? = null
    internal var resumed = false

    private val isActivityLaunched: Boolean
        get() {
            val activitiesInStage = instance.getActivitiesInStage(Stage.RESUMED)
            for (activity in activitiesInStage) {
                if (activity.javaClass.name == activityToWaitClassName) {
                    return true
                }
            }
            return false
        }

    init {
        instance = ActivityLifecycleMonitorRegistry.getInstance()
    }

    override fun getName(): String {
        return this.javaClass.name
    }

    override fun isIdleNow(): Boolean {
        resumed = isActivityLaunched
        if (resumed && resourceCallback != null) {
            resourceCallback!!.onTransitionToIdle()
        }

        return resumed
    }

    override fun registerIdleTransitionCallback(resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}


class ElapsedTimeIdlingResource(private val waitingTime: Long) : IdlingResource {

    private val startTime: Long
    private var resourceCallback: IdlingResource.ResourceCallback? = null

    init {
        this.startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return ElapsedTimeIdlingResource::class.java.name + ":" + waitingTime
    }

    override fun isIdleNow(): Boolean {
        val elapsed = System.currentTimeMillis() - startTime
        val idle = elapsed >= waitingTime
        if (idle) {
            resourceCallback!!.onTransitionToIdle()
        }
        return idle
    }

    override fun registerIdleTransitionCallback(
            resourceCallback: IdlingResource.ResourceCallback) {
        this.resourceCallback = resourceCallback
    }

}

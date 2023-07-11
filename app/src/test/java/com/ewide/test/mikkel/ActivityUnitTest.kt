package com.ewide.test.mikkel

import androidx.test.core.app.ActivityScenario
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import junit.framework.Assert.assertNotNull
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@LargeTest
class ActivityTest {
    private var mainActivityScenario: ActivityScenario<MainActivity>? = null

    @Before
    fun setUp() {
        mainActivityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        mainActivityScenario?.close()
    }

    @Test
    @Config(sdk = [30])
    fun testMainActivityNotNull() {
        mainActivityScenario?.onActivity { activity: MainActivity? ->
            assertNotNull(activity)
        }
    }
}

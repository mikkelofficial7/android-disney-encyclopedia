package com.ewide.test.mikkel

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.ewide.test.mikkel.fragment.MainPageFragment
import junit.framework.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config


@RunWith(AndroidJUnit4::class)
@LargeTest
class FragmentUnitTest {
    @Test
    @Config(sdk = [30])
    fun testInitFragmentMain() {
        val scenario = launchFragmentInContainer<MainPageFragment>(
            initialState = Lifecycle.State.INITIALIZED
        )

        assertNotNull(scenario)
    }
}

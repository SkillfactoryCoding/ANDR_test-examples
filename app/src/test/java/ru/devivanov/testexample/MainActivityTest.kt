package ru.devivanov.testexample

import android.content.Intent
import android.widget.Button
import androidx.test.core.app.ActivityScenario
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.Shadows
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowDisplay

@RunWith(RobolectricTestRunner::class)
@Config(maxSdk = 21)
class MainActivityTest {
    @Test
    fun `should launch AnotherActivity on button push`() {
        Robolectric.setupActivity(MainActivity::class.java)
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        activityScenario.onActivity {
            it.findViewById<Button>(R.id.button).performClick()
            val expectedIntent = Intent(it, AnotherActivity::class.java)
            val actualIntent = Shadows.shadowOf(RuntimeEnvironment.application).nextStartedActivity
            val toasts = Shadows.shadowOf(RuntimeEnvironment.application).shownToasts
            Assert.assertEquals(0, toasts.size)
            Assert.assertEquals(expectedIntent.component, actualIntent.component)
        }
    }
}
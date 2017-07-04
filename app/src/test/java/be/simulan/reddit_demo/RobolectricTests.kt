package be.simulan.reddit_demo

import be.simulan.reddit_demo.mvp.views.MainActivity
import org.junit.Assert
import org.junit.Test
import org.robolectric.Robolectric

class RobolectricTests : AndroidTest() {

    @Test fun activityFromController() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val activity = activityController.get()
        Assert.assertNotNull(activity)
    }

    @Test fun activity() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        Assert.assertNotNull(activity)
    }
    @Test fun activityRunsOnViewReady() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val activity = activityController.get()
        activityController.setup()
        assert(activity.calledOnViewReady)
    }
}
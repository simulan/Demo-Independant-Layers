package be.simulan.reddit_demo

import be.simulan.reddit_demo.thread.views.ThreadsActivity
import org.junit.Assert
import org.junit.Test
import org.robolectric.Robolectric

class RobolectricTests : AndroidTest() {

    @Test fun activityFromController() {
        val activityController = Robolectric.buildActivity(ThreadsActivity::class.java)
        val activity = activityController.get()
        Assert.assertNotNull(activity)
    }
    @Test fun activity() {
        val activity = Robolectric.setupActivity(ThreadsActivity::class.java)
        Assert.assertNotNull(activity)
    }
}
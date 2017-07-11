package be.simulan.reddit_demo.mvp.views

import be.simulan.reddit_demo.AndroidTest
import kotlinx.android.synthetic.main.activity_threads.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.robolectric.Robolectric

class ThreadsActivityTest : AndroidTest() {
    lateinit var activity: ThreadsActivity

    @Before
    fun setUp() {
        activity = Robolectric.buildActivity(ThreadsActivity::class.java).get()
        activity = Mockito.spy(activity)
    }

    @Test
    fun shouldPass() {
        assert(true)
    }

    @Test
    fun mockitoCanMockFinals() {
        activity.onCreate(null, null)
        Mockito.verify(activity).onCreate(null, null)
    }

    @Test
    fun listInitialized() {
        activity.onCreate(null, null)
        assert(activity.recycler != null)
        assert(activity.recycler.adapter != null)
    }


}
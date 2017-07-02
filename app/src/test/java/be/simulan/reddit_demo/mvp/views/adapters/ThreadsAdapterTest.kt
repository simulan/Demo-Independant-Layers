package be.simulan.reddit_demo.mvp.views.adapters

import be.simulan.reddit_demo.BuildConfig
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.views.MainActivity
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.android.controller.ActivityController
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = intArrayOf(25), constants = BuildConfig::class)
class ThreadsAdapterTest {

    @Test fun activity() {
        val activity = Robolectric.buildActivity(MainActivity::class.java)
        Assert.assertNotNull(activity.get())
    }

    @Test fun getItemCount() {
        val adapter = ThreadsAdapter()
        assert(adapter.itemCount==0)
    }

    @Test fun add() {
        val adapter = ThreadsAdapter()
        val threads = listOf(RThread())
        adapter.add(threads)
        assert(adapter.itemCount == 1)
    }
    @Test fun clear() {
        val adapter = ThreadsAdapter()
        val threads = listOf(RThread())
        adapter.add(threads)
        adapter.clear()
        assert(adapter.itemCount == 0)
    }
    @Test fun getLastId() {
        val adapter = ThreadsAdapter()
        val id = "last"
        val first = RThread()
        val second = RThread()
        val threads = listOf(first, second)
        second.id = id
        adapter.add(threads)
        assert(adapter.getLastId() == id)
    }





}
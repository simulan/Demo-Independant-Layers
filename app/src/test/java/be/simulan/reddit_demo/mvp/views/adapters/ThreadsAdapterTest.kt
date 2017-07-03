package be.simulan.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import be.simulan.reddit_demo.CustomRunner
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.views.MainActivity
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadViewHolder
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.android.controller.ActivityController

@RunWith(CustomRunner::class)
class ThreadsAdapterTest {
    @Test fun activity() {
        val activityController = Robolectric.buildActivity(MainActivity::class.java)
        val activity = activityController.get()
        Assert.assertNotNull(activity)
    }
    @Test fun getViewHolder() {
        val activityController : ActivityController<MainActivity> = Robolectric.buildActivity(MainActivity::class.java)
        val activity = activityController.get()
        val adapter = ThreadsAdapter()
        val recycler = activity.findViewById(R.id.recycler) as RecyclerView
        val layoutId = R.layout.row_thread

        val thread = RThread()
        thread.title = "threadName"

        recycler.adapter = adapter
        adapter.add(listOf(thread))
        try{
            val vh : ThreadViewHolder = adapter.onCreateViewHolder(recycler,layoutId) as ThreadViewHolder
            assert(true)
        }catch(exception : Exception) {
            assert(false)
        }
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
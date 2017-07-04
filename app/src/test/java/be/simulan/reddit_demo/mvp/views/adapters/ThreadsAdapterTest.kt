package be.simulan.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import be.simulan.reddit_demo.AndroidTest
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.views.MainActivity
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadViewHolder
import org.junit.Test
import org.robolectric.Robolectric

class ThreadsAdapterTest : AndroidTest() {

    @Test fun getViewHolder() {
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val adapter = ThreadsAdapter()
        val recycler = activity.findViewById(R.id.recycler) as RecyclerView
        val layoutId = R.layout.row_thread

        val thread = RThread()
        thread.title = "threadName"

        recycler.adapter = adapter
        val list = listOf(thread)
        adapter.add(list)
        try {
            val vh: ThreadViewHolder = adapter.onCreateViewHolder(recycler, layoutId) as ThreadViewHolder
            assert(vh is ThreadViewHolder)
        } catch(exception: Exception) {
            assert(false)
        }
    }
    @Test fun getItemCount() {
        val adapter = ThreadsAdapter()
        assert(adapter.itemCount == 0)
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
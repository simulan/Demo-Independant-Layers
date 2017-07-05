package be.simulan.reddit_demo.mvp.views.adapters

import be.simulan.reddit_demo.AndroidTest
import be.simulan.reddit_demo.mvp.models.data.RThread
import org.junit.Test

class ThreadsAdapterTest : AndroidTest() {
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
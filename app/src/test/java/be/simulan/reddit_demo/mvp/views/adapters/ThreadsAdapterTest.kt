package be.simulan.reddit_demo.mvp.views.adapters

import be.simulan.reddit_demo.AndroidTest
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import org.junit.Test

class ThreadsAdapterTest : AndroidTest() {
    @Test fun getItemCount() {
        val adapter = ThreadsAdapter()
        assert(adapter.itemCount == 0)
    }
    @Test fun add() {
        val adapter = ThreadsAdapter()
        val threads = listOf(ThreadItem())
        adapter.add(threads)
        assert(adapter.itemCount == 1)
    }
    @Test fun clear() {
        val adapter = ThreadsAdapter()
        val threads = listOf(ThreadItem())
        adapter.add(threads)
        adapter.clear()
        assert(adapter.itemCount == 0)
    }
    @Test fun getLastId() {
        val adapter = ThreadsAdapter()
        val id = "last"
        val first = ThreadItem()
        val second = ThreadItem()
        val threads = listOf(first, second)
        second.id = id
        adapter.add(threads)
        assert(adapter.getLastId() == id)
    }
}
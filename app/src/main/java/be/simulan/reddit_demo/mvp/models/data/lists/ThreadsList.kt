package be.simulan.reddit_demo.mvp.models.data.lists

import android.support.v7.util.SortedList
import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import java.util.*


/**
 * @author Simulan
 * @version 1.0.0
 * @since 19/06/2017
 */


//todo : reimplement comparator to toggle between fields

/**
 * Implementation of Reddit Threads Data Controller
 */
class ThreadsList() : DataListListener<ThreadHeader> {
     var items: SortedList<ThreadHeader> = SortedList(ThreadHeader::class.java, SortedThreadsCallBack())
    private var listener: RecyclerAdapterListener? = null
    private val comparator: Comparator<ThreadHeader> = ThreadsComparator()

    //P
    fun setListener(listener: RecyclerAdapterListener) {
        this.listener = listener
    }

    //I DataListListener
    override fun size(): Int {
        return items.size()
    }

    override fun get(i: Int): ThreadHeader {
        return items.get(i)
    }


    //C ThreadsComparator
    inner class ThreadsComparator : Comparator<ThreadHeader> {
        override fun compare(o1: ThreadHeader?, o2: ThreadHeader?): Int = o1!!.title.compareTo(o2!!.title)
    }

    //C SortedList.Callback
    inner class SortedThreadsCallBack : SortedList.Callback<ThreadHeader>() {
        override fun onInserted(position: Int, count: Int) {
            listener?.notifyItemRangeInserted(position, count)
        }

        override fun onRemoved(position: Int, count: Int) {
            listener?.notifyItemRangeRemoved(position, count)
        }

        override fun onMoved(fromPosition: Int, toPosition: Int) {
            listener?.notifyItemMoved(fromPosition, toPosition)
        }

        override fun onChanged(position: Int, count: Int) {
            listener?.notifyItemRangeChanged(position, count)
        }

        override fun areItemsTheSame(p0: ThreadHeader?, p1: ThreadHeader?): Boolean = p0!!.id == p1!!.id
        override fun compare(p0: ThreadHeader?, p1: ThreadHeader?): Int = comparator.compare(p0, p1)
        override fun areContentsTheSame(p0: ThreadHeader?, p1: ThreadHeader?): Boolean = p0!!.title==p1!!.title
    }

    //C Editor
    /*fun add(items: List<RThread>) {
        Collections.sort(items, comparator)
        items.addAll(items)
    }
    fun add(item: RThread) = items.add(item)
    fun remove(item: RThread) = items.remove(item)
    fun remove(items: List<RThread>) {
        for (item in items) {
            items.remove(item)
        }
    }
    fun replaceAll(items: List<RThread>) {
        val itemsToRemove = filter(object : Filter<RThread> {
            override fun test(item: RThread): Boolean {
                return !items.contains(item)
            }
        })
        for (i in itemsToRemove.indices.reversed()) {
            val item = itemsToRemove[i]
            items.remove(item)
        }
        items.addAll(items)
    }
    fun removeAll() { items.clear() }
    */

    fun filter(filter: Filter<ThreadHeader>): List<ThreadHeader> {
        val list = mutableListOf<ThreadHeader>()
        var i = 0
        val count = items.size()
        while (i < count) {
            val item = items.get(i)
            if (filter.test(item)) {
                list.add(item)
            }
            i++
        }
        return list
    }

    enum class ListMode {
        LIST, FILTER
    }
}
interface Filter<T> {
    fun test(item: T): Boolean
}

/**
 * Interface for decoupling an adapter from its data
 */
interface RecyclerAdapterListener {
    fun notifyItemRangeInserted(pos: Int, count: Int)
    fun notifyItemRangeRemoved(pos: Int, count: Int)
    fun notifyItemMoved(pos: Int, count: Int)
    fun notifyItemRangeChanged(pos: Int, count: Int)
}

/**
 * Interface for decoupling data from an adapter
 */
interface DataListListener<T> {
    fun size(): Int
    fun get(i: Int): T
}

/**
 * Interface for executing batch actions on lists
 */

package be.sanderdebleecker.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import be.sanderdebleecker.reddit_demo.mvp.views.adapters.viewholders.ThreadViewHolder

/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */

class ThreadsAdapter() : RecyclerView.Adapter<ThreadViewHolder>() {
    private val MAX_ITEMS_BUFFERED : Int = 50
    private val LIMIT : Int = 10
    private var list = ArrayList<RThread>()
    val lastId
        get() = list.last().id
    val firstId
        get() = list.first().id

    //Adapter
    override fun onCreateViewHolder(viewGroup: ViewGroup, index: Int): ThreadViewHolder? = ThreadViewHolder(viewGroup)
    override fun onBindViewHolder(vh: ThreadViewHolder?, index: Int) { vh?.bind(getItem(index)) }
    override fun getItemCount(): Int = list.size
    private fun getItem(index: Int): RThread = list[index]

    //DA
    fun add(additions : List<RThread>) {
        val prevItemCount : Int = itemCount
        list.addAll(additions)
        notifyItemRangeInserted(prevItemCount,additions.size)
        if(itemCount > MAX_ITEMS_BUFFERED) trimStart(LIMIT)
    }
    fun prepend(additions: List<RThread>) {
        list.addAll(0, additions)
        notifyItemRangeInserted(0, additions.size)
        if(itemCount > MAX_ITEMS_BUFFERED) trimEnd(LIMIT)
    }
    fun clear() {
        val prevItemCount : Int = itemCount
        list.clear()
        notifyItemRangeRemoved(0,prevItemCount)
    }
    //MEM
    fun trimStart(trimCount: Int) {
        val trimEnd : Int = trimCount-1
        trim(trimEnd,0)
        notifyItemRangeRemoved(0,trimCount)
    }
    fun trimEnd(trimCount: Int) {
        val trimEnd : Int = list.size - 1
        val trimStart: Int = trimEnd - trimCount
        trim(trimEnd,trimStart)
    }
    fun trim(end : Int,start : Int,count : Int = end-start+1) {
        for(i in end downTo start) {
            list.removeAt(i)
        }
        notifyItemRangeRemoved(start,count)
    }
}



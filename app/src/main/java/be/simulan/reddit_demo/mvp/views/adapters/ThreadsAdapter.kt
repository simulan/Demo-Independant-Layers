package be.simulan.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadItemViewHolder
import be.simulan.reddit_demo.mvp.views.inflate
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ThreadsAdapter() : RecyclerView.Adapter<ThreadItemViewHolder>(),ThreadsArrayList {
    private val list = ArrayList<ThreadItem>()
    private val threadClickSubject: PublishSubject<String> = PublishSubject.create()
    private val thumbnailClickSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreateViewHolder(container: ViewGroup, index: Int): ThreadItemViewHolder? = ThreadItemViewHolder(container.inflate(R.layout.row_thread))
    override fun onBindViewHolder(viewHolder: ThreadItemViewHolder?, index: Int) {
        viewHolder?.bind(getItem(index))
        viewHolder?.getThumbnailClicks()!!.subscribe(thumbnailClickSubject)
        viewHolder?.getThreadClicks()!!.subscribe(threadClickSubject)
    }
    override fun getItemCount(): Int = list.size
    private fun getItem(index: Int): ThreadItem = list[index]

    override fun add(additions : List<ThreadItem>) {
        val prevItemCount : Int = itemCount
        list.addAll(additions)
        notifyItemRangeInserted(prevItemCount,additions.size)
    }
    override fun clear() {
        val prevItemCount : Int = itemCount
        list.clear()
        notifyItemRangeRemoved(0,prevItemCount)
    }
    override fun getLastId() : String = list.last().id

    fun getThreadClickSubject() : Observable<String> = threadClickSubject
    fun getThumbnailClickSubject() : Observable<String> = thumbnailClickSubject
}



package be.simulan.reddit_demo.mvp.views.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadVH
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.ThreadVHListener
import be.simulan.reddit_demo.mvp.views.inflate
import com.jakewharton.rxbinding2.view.RxView
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ThreadsAdapter() : RecyclerView.Adapter<ThreadVH>(),ThreadsArrayList {
    private val list = ArrayList<ThreadHeader>()
    private val clickSubject: PublishSubject<ThreadVHListener> = PublishSubject.create<ThreadVHListener>()

    override fun onCreateViewHolder(container: ViewGroup, index: Int): ThreadVH? {
        val itemView : View = container.inflate(R.layout.row_thread)
        val viewHolder = ThreadVH(itemView)
        delegateViewHolderEvents(viewHolder,container,index)
        return viewHolder
    }
    private fun delegateViewHolderEvents(viewHolder : ThreadVH, container: ViewGroup, index : Int) {
        RxView.clicks(viewHolder.itemView)
                .takeUntil(RxView.detaches(container))
                .map({ viewHolder })
                .subscribe(clickSubject)
    }

    override fun onBindViewHolder(vh: ThreadVH?, index: Int) { vh?.bind(getItem(index)) }
    override fun getItemCount(): Int = list.size
    private fun getItem(index: Int): ThreadHeader = list[index]

    override fun add(additions : List<ThreadHeader>) {
        val prevItemCount : Int = itemCount
        list.addAll(additions)
        notifyItemRangeInserted(prevItemCount,additions.size)
    }
    override fun clear() {
        val prevItemCount : Int = itemCount
        list.clear()
        notifyItemRangeRemoved(0,prevItemCount)
    }
    override fun getLastId() : String {
        return list.last().id
    }
    fun getClickSubject(): Observable<ThreadVHListener> = clickSubject
}



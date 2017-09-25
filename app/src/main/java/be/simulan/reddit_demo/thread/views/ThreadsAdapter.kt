package be.simulan.reddit_demo.thread.views

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.thread.presenters.ThreadsProvider
import be.simulan.reddit_demo.inflate
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class ThreadsAdapter() : RecyclerView.Adapter<ThreadItemViewHolder>() {
    private var provider : ThreadsProvider? = null
    private val threadClickSubject: PublishSubject<String> = PublishSubject.create()
    private val thumbnailClickSubject: PublishSubject<String> = PublishSubject.create()

    override fun onCreateViewHolder(container: ViewGroup, index: Int): ThreadItemViewHolder? = ThreadItemViewHolder(container.inflate(R.layout.row_thread))
    override fun onBindViewHolder(viewHolder: ThreadItemViewHolder, index: Int) {
        viewHolder.bind(provider!!.getItem(index))
        viewHolder.getThumbnailClicks().subscribe(thumbnailClickSubject)
        viewHolder.getThreadClicks().subscribe(threadClickSubject)
    }
    override fun getItemCount(): Int = provider?.getItemCount() ?: 0

    fun setProvider(provider : ThreadsProvider) {
        this.provider = provider
    }
    fun getThreadClickSubject() : Observable<String> = threadClickSubject
    fun getThumbnailClickSubject() : Observable<String> = thumbnailClickSubject
}



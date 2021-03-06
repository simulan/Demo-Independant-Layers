package be.simulan.reddit_demo.thread.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.data_access.api.IRedditApi
import be.simulan.reddit_demo.thread.models.Category
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.thread.models.ThumbnailItem
import be.simulan.reddit_demo.LIMIT
import be.simulan.reddit_demo.application.presenters.BasePresenter
import be.simulan.reddit_demo.thread.views.ThreadsView
import be.simulan.reddit_demo.thread.views.ThreadsAdapter
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

open class ThreadsPresenterImpl @Inject constructor() : BasePresenter<ThreadsView>(), ThreadsPresenter, ThreadsProvider, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    @Inject internal lateinit var api: IRedditApi
    @Inject internal lateinit var threadsAdapter: ThreadsAdapter

    private val threads = ArrayList<ThreadItem>()
    private var threadsObserver: ThreadsObserver? = null
    private var thumbnailObserver: ThumbnailObserver? = null
    private var previousCategory: Category = Category.new
    private var category: Category = Category.new
    private var query : String = ""

    override fun getAdapter(): ThreadsAdapter = threadsAdapter
    override fun bindAdapter() {
        threadsAdapter.setProvider(this)
        bindViewHolderEvents()
    }
    private fun bindViewHolderEvents() {
        threadsAdapter.getThreadClickSubject().subscribe { getView().showThread(it) }
        threadsAdapter.getThumbnailClickSubject().subscribe {
            loadThumbnailIfThreadTypeIsImage(it)
        }
    }
    override fun loadThreads(): Boolean {
        if (threadObserverIsAvailable()) {
            threadsObserver = ThreadsObserver()
            val lastId = getLastIdOrEmpty()
            when (category) {
                Category.search -> api.searchThreads(q=query,after = lastId,count=threads.size,restrict_sr = true)
                else -> api.listThreads(after = lastId, limit = LIMIT, count = threads.size).subscribeAsync(threadsObserver!!)
            }
            return true
        } else {
            return false
        }
    }
    private fun threadObserverIsAvailable() = threadsObserver == null
    private fun getLastIdOrEmpty() : String = threads.lastOrNull()?.id ?: ""
    override fun switchCategory(newCategory: Category) {
        previousCategory = category
        category = newCategory
    }

    fun loadThumbnailIfThreadTypeIsImage(id: String) {
        if(threads.filter{ it.id == id }.single().type == ThreadItem.Type.Image) {
            loadThumbnail(id)
        }
    }
    fun loadThumbnail(id: String) {
        if (thumbnailObserverIsAvailable()) {
            thumbnailObserver = ThumbnailObserver()
            api.getThumbnailById(id).subscribeAsync(thumbnailObserver!!)
        }
    }
    private fun thumbnailObserverIsAvailable() = thumbnailObserver == null

    inner class ThreadsObserver : Observer<Array<ThreadItem>> {
        lateinit var streamDisposer: Disposable

        override fun onNext(t: Array<ThreadItem>) {
                addThreads(t.asList())
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenterImpl.threadsObserver = null
        }
        override fun onError(e: Throwable) {
            getView().showToast(e.message!!)
        }
        override fun onSubscribe(d: Disposable) {
            streamDisposer = d
        }
    }
    inner class ThumbnailObserver : Observer<ThumbnailItem> {
        lateinit var streamDisposer: Disposable

        override fun onNext(t: ThumbnailItem) {
            getView().showThumbnail(t)
        }
        override fun onSubscribe(d: Disposable) {
            streamDisposer = d
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenterImpl.thumbnailObserver = null
        }
        override fun onError(e: Throwable) {
            getView().showToast(e.message!!)
        }
    }

    override fun getItem(position: Int): ThreadItem = threads[position]
    override fun getItemCount(): Int = threads.size
    private fun addThreads(additions: List<ThreadItem>) {
        val oldSize = threads.size
        threads.addAll(additions)
        threadsAdapter.notifyItemRangeInserted(oldSize, additions.size)
    }
    private fun clearThreads() {
        val oldSize = threads.size
        threads.clear()
        threadsAdapter.notifyItemRangeRemoved(0,oldSize)
    }

    override fun onQueryTextSubmit(query: String?): Boolean = true
    override fun onQueryTextChange(newText: String?): Boolean {
        cancelRequests()
        if (category != Category.search) {
            previousCategory = category
            category = Category.search
        }
        query = newText ?: ""
        loadThreads()
        return true
    }
    override fun onClose(): Boolean {
        cancelRequests()
        reset()
        return false
    }
    private fun cancelRequests() {
        threadsObserver?.streamDisposer?.dispose()
        threadsObserver = null
    }
    private fun reset() {
        category = previousCategory
        clearThreads()
        loadThreads()
    }


}

package be.simulan.reddit_demo.mvp.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.mvp.models.data.Category
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay
import be.simulan.reddit_demo.mvp.models.static.LIMIT
import be.simulan.reddit_demo.mvp.views.ThreadsView
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class ThreadsPresenterImpl @Inject constructor() : BasePresenter<ThreadsView>(), ThreadsPresenter, ThreadsProvider, SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    @Inject protected lateinit var api: IRedditApi
    @Inject protected lateinit var threadsAdapter: ThreadsAdapter

    private val threads = ArrayList<ThreadItem>()
    private var threadsObserver: ThreadsObserver? = null
    private var thumbnailObserver: ThumbnailObserver? = null
    private var previousCategory: Category = Category.new
    private var category: Category = Category.new
    private var query : String = ""

    override fun getAdapter(): ThreadsAdapter = threadsAdapter
    override fun bindAdapter() {
        bindViewHolderEvents()
        threadsAdapter.setProvider(this)
    }
    private fun bindViewHolderEvents() {
        threadsAdapter.getThreadClickSubject().subscribe { getView().showThread(it) }
        threadsAdapter.getThumbnailClickSubject().subscribe { loadThumbnail(it) }
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

    fun loadThumbnail(id: String) {
        if (thumbnailObserverIsAvailable()) {
            thumbnailObserver = ThumbnailObserver()
            api.getThumbnailById(id).subscribeAsync(thumbnailObserver!!)
        }
    }
    private fun thumbnailObserverIsAvailable() = thumbnailObserver == null

    inner class ThreadsObserver : Observer<Array<ThreadItem>> {
        lateinit var streamDisposer: Disposable

        override fun onNext(t: Array<ThreadItem>?) {
            if (t != null) {
                addThreads(t.asList())
            }
        }

        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenterImpl.threadsObserver = null
        }

        override fun onError(e: Throwable?) {
            getView().showToast(e!!.message!!)
        }

        override fun onSubscribe(d: Disposable?) {
            streamDisposer = d!!
        }
    }
    inner class ThumbnailObserver : Observer<ThumbnailOverlay> {
        lateinit var streamDisposer: Disposable

        override fun onNext(t: ThumbnailOverlay?) {
            if (t != null) {
                getView().showThumbnail(t)
            }
        }

        override fun onSubscribe(d: Disposable?) {
            streamDisposer = d!!
        }

        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenterImpl.thumbnailObserver = null
        }

        override fun onError(e: Throwable?) {
            getView().showToast(e!!.message!!)
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

    private fun <T : Any> Observable<T>.subscribeAsync(observer: Observer<T>) {
        this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }
}

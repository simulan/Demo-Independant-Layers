package be.simulan.reddit_demo.mvp.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay
import be.simulan.reddit_demo.mvp.views.ThreadsView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

enum class Command { NEW, SEARCH }
open class ThreadsPresenter @Inject constructor() : BasePresenter<ThreadsView>(), SearchView.OnQueryTextListener, SearchView.OnCloseListener  {
    @Inject protected lateinit var api: IRedditApi
    private var threadsObserver: ThreadsObserver? = null
    private var thumbnailObserver : ThumbnailObserver? = null
    private var previousCommand: Command = Command.NEW
    private var command: Command = Command.NEW

    fun getThreads(command: Command = this.command,after : String="",limit : Int=20,count : Int=0,query: String="",restrict_sr : Boolean=true) : Boolean{
        if(threadObserverIsAvailable()) {
            threadsObserver = ThreadsObserver()
            when(command) {
                Command.NEW -> api.listThreads(after = after,limit = limit,count = count).subscribeAsync(threadsObserver!!)
                Command.SEARCH -> api.searchThreads(after = after,limit = limit,count = count,q=query,restrict_sr = restrict_sr).subscribeAsync(threadsObserver!!)
            }
            return true
        }else {
            return false
        }
    }
    private fun threadObserverIsAvailable() = threadsObserver == null
    fun getThumbnail(id: String) {
        if(thumbnailObserverIsAvailable()) {
            thumbnailObserver = ThumbnailObserver()
            api.getThumbnailById(id).subscribeAsync(thumbnailObserver!!)
        }
    }
    private fun thumbnailObserverIsAvailable() = thumbnailObserver == null

    inner class ThreadsObserver : Observer<Array<ThreadItem>> {
        lateinit var streamDisposer : Disposable

        override fun onNext(t: Array<ThreadItem>?) {
            if(t != null) {
                getView().showThreads(t.asList())
            }
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenter.threadsObserver =null
        }
        override fun onError(e: Throwable?) {
            getView().showToast(e!!.message!!)
        }
        override fun onSubscribe(d: Disposable?) {
            streamDisposer = d!!
        }
    }
    inner class ThumbnailObserver : Observer<ThumbnailOverlay> {
        lateinit var streamDisposer : Disposable

        override fun onNext(t: ThumbnailOverlay?) {
            if(t != null) {
                getView().showThumbnail(t)
            }
        }
        override fun onSubscribe(d: Disposable?) {
            streamDisposer = d!!
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenter.thumbnailObserver = null
        }
        override fun onError(e: Throwable?) {
            getView().showToast(e!!.message!!)
        }
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }
    override fun onQueryTextChange(newText: String?): Boolean {
        cancelRequests()
        if(command != Command.SEARCH) {
            previousCommand = command
            command = Command.SEARCH
        }
        getView().clearThreads()
        getThreads(query= newText?:"")
        return true
    }
    override fun onClose(): Boolean {
        cancelRequests()
        reset()
        return false
    }
    private fun cancelRequests() {
        threadsObserver?.streamDisposer?.dispose()
        threadsObserver =null
    }
    private fun reset() {
        command = previousCommand
        getView().clearThreads()
        getThreads()
    }

    private fun <T : Any> Observable<T>.subscribeAsync(observer : Observer<T>) {
        this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }
}
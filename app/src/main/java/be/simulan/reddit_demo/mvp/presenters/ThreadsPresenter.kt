package be.simulan.reddit_demo.mvp.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
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
    var threadsObserver: ThreadsObserver? = null
    var thumbnailObserver : ThreadsObserver? = null
    var previousCommand: Command = Command.NEW
    var command: Command = Command.NEW

    fun getThreads(command: Command = this.command,after : String="",limit : Int=20,count : Int=0,query: String="",restrict_sr : Boolean=true) : Boolean{
        if(threadObserverIsAvailable()) {
            threadsObserver = ThreadsObserver()
            when(command) {
                Command.NEW -> api.listThreads(after = after,limit = limit,count = count).async()
                Command.SEARCH -> api.searchThreads(after = after,limit = limit,count = count,q=query,restrict_sr = restrict_sr).async()
            }
            return true
        }else {
            return false
        }
    }

    private fun threadObserverIsAvailable() = threadsObserver == null
    private fun Observable<Array<ThreadHeader>>.async() = this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
    inner class ThreadsObserver : Observer<Array<ThreadHeader>> {
        lateinit var streamDisposer : Disposable

        override fun onNext(t: Array<ThreadHeader>?) {
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
            this@ThreadsPresenter.threadsObserver =null
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

    fun  getThumbnail(id: String) {
        api.getThumbnailById(id)
    }
}

package be.simulan.reddit_demo.mvp.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.mvp.models.data.RThread
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
    var observer: ThreadsObserver? = null
    var previousCommand: Command = Command.NEW
    var command: Command = Command.NEW

    fun getThreads(command: Command = this.command,after : String="",limit : Int=20,count : Int=0,query: String="",restrict_sr : Boolean=true) : Boolean{
        if(observerIsAvailable()) {
            observer = ThreadsObserver()
            when(command) {
                Command.NEW -> api.listThreads(after = after,limit = limit,count = count).subscribeOn(Schedulers.computation()).async()
                Command.SEARCH -> api.searchThreads(after = after,limit = limit,count = count,q=query,restrict_sr = restrict_sr).async()
            }
            return true
        }else {
            return false
        }
    }
    private fun observerIsAvailable() = observer == null
    private fun Observable<Array<RThread>>.async() = this.observeOn(AndroidSchedulers.mainThread()).subscribe(observer)

    open inner class ThreadsObserver : Observer<Array<RThread>> {
        lateinit var streamDisposer : Disposable

        override fun onNext(t: Array<RThread>?) {
            if(t != null) {
                getView().showThreads(t.asList())
            }
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadsPresenter.observer =null
        }
        override fun onError(e: Throwable?) {
            getView().showToast(e!!.message!!)
        }
        override fun onSubscribe(d: Disposable?) {
            streamDisposer = d!!
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
        observer?.streamDisposer?.dispose()
        observer =null
    }
    private fun reset() {
        command = previousCommand
        getView().clearThreads()
        getThreads()
    }
}

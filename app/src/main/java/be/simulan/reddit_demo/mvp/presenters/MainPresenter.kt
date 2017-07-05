package be.simulan.reddit_demo.mvp.presenters

import android.support.v7.widget.SearchView
import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.views.ThreadsView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class MainPresenter @Inject constructor() : BasePresenter<ThreadsView>(), SearchView.OnQueryTextListener, SearchView.OnCloseListener  {
    @Inject protected lateinit var api: IRedditApi
    var threadsObserver : ThreadsObserver? = null
    var prevCommand: Command = Command.NEW
    var command: Command = Command.NEW

    fun getThreads(command: Command = this.command,before : String="",after : String="",limit : Int=20,count : Int=0,query: String="",restrict_sr : Boolean=true) : Boolean{
        if(threadsObserver==null) {
            threadsObserver = ThreadsObserver()
            when(command) {
                Command.NEW -> {
                    if(before!="") api.listThreadsBefore(before = before,limit = limit,count = count).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
                    else api.listThreads(after = after,limit = limit,count = count).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
                }
                Command.SEARCH -> api.searchThreads(after = after,limit = limit,count = count,q=query,restrict_sr = restrict_sr).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
            }
            return true
        }else {
            return false
        }
    }
    open inner class ThreadsObserver(val append : Boolean = true) : Observer<Array<RThread>> {
        lateinit var streamDisposer : Disposable
        //mode
        override fun onNext(t: Array<RThread>?) {
            if(t != null) {
                getView().showThreads(t.asList())
            }
        }
        override fun onComplete() {
            streamDisposer.dispose()
            this@MainPresenter.threadsObserver=null
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
        resetRequests()
        if(command != Command.SEARCH) {
            prevCommand = command
            command = Command.SEARCH
        }
        getView().clearThreads()
        getThreads(query= newText?:"")
        return true
    }
    override fun onClose(): Boolean {
        resetRequests()
        command = prevCommand
        getView().clearThreads()
        getThreads()
        return false
    }
    private fun resetRequests() {
        threadsObserver?.streamDisposer?.dispose()
        threadsObserver=null
    }
}

package be.sanderdebleecker.reddit_demo.mvp.presenters

import be.sanderdebleecker.reddit_demo.da.apis.IRedditService
import be.sanderdebleecker.reddit_demo.mvp.models.json.ThreadsEnvelope
import be.sanderdebleecker.reddit_demo.mvp.views.MainView
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */

open class MainPresenter @Inject constructor() : BasePresenter<MainView>() {
    @Inject protected lateinit var service : IRedditService
    var threadsObserver : ThreadsObserver? = null

    fun getThreads() {
        threadsObserver = ThreadsObserver()
        service.listPosts().subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
    }
    fun getThreads(after : String,limit : Int,count : Int) : Boolean {
        val loading = threadsObserver!=null
        if(!loading) {
            threadsObserver = ThreadsObserver()
            service.listPosts(after = after,limit = limit,count = count).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(threadsObserver)
        }
        return loading
    }

    // stream handlers
    inner class ThreadsObserver : Observer<ThreadsEnvelope> {
        lateinit var streamDisposer : Disposable
        override fun onNext(t: ThreadsEnvelope?) {
            if(t != null) {
                getView().showThreads(t.viewable())
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


}
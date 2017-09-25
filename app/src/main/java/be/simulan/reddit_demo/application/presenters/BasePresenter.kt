package be.simulan.reddit_demo.application.presenters

import be.simulan.reddit_demo.application.views.BaseView
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

open class BasePresenter<V: BaseView> {
    @Inject protected lateinit var mView: V

    protected fun getView() : V {
        return mView;
    }
    protected fun <T : Any> Observable<T>.subscribeAsync(observer: Observer<T>) {
        this.subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer)
    }
}
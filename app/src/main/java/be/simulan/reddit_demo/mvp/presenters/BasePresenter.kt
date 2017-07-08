package be.simulan.reddit_demo.mvp.presenters

import be.simulan.reddit_demo.mvp.views.BaseView
import javax.inject.Inject

open class BasePresenter<V: BaseView> {
    @Inject protected lateinit var mView: V

    protected fun getView() : V {
        return mView;
    }
}
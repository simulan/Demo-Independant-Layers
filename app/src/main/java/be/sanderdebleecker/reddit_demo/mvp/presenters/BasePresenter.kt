package be.sanderdebleecker.reddit_demo.mvp.presenters

import be.sanderdebleecker.reddit_demo.mvp.views.BaseView
import javax.inject.Inject

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
open class BasePresenter<V: BaseView> {
    @Inject protected lateinit var mView: V

    protected fun getView() : V {
        return mView;
    }
}
package be.sanderdebleecker.reddit_demo.mvp.views

import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
interface MainView : BaseView {
    fun  showThreads(threads: MutableList<RThread>)
}
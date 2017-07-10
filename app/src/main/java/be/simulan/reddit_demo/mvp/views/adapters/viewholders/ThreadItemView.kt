package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import io.reactivex.Observable

interface ThreadHeaderView {
    fun getThreadId(): String
    fun getThumbnailClicks(): Observable<String>
    fun getThreadClicks(): Observable<String>
}
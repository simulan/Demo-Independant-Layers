package be.simulan.reddit_demo.thread.views

import io.reactivex.Observable

interface ThreadItemView {
    fun getThreadId(): String
    fun getThumbnailClicks(): Observable<String>
    fun getThreadClicks(): Observable<String>
}
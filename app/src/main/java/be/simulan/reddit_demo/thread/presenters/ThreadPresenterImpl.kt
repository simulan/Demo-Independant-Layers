package be.simulan.reddit_demo.thread.presenters

import be.simulan.reddit_demo.application.presenters.BasePresenter
import be.simulan.reddit_demo.comments.models.Comment
import be.simulan.reddit_demo.data_access.api.IRedditApi
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.thread.views.ThreadView
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ThreadPresenterImpl @Inject constructor() : BasePresenter<ThreadView>(), ThreadPresenter {
    @Inject internal lateinit var api: IRedditApi
    private var commentsObserver: CommentsObserver? = null
    private var threadObserver: ThreadObserver? = null

    override fun loadThread(id: String) {
        if (commentsObserver == null) {
            threadObserver = ThreadObserver()
            api.getThreadById(id).subscribeAsync(threadObserver!!)
        }

    }

    override fun loadComments(idOfThread: String) {
        if (commentsObserver == null) {
            commentsObserver = CommentsObserver()
            api.listComments(id = idOfThread.split(Regex("[_]"), 0)[1]).subscribeAsync(commentsObserver!!)
        }
    }

    inner class ThreadObserver : Observer<ThreadItem> {
        lateinit var streamDisposer: Disposable
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadPresenterImpl.threadObserver = null
        }

        override fun onSubscribe(d: Disposable) {
            streamDisposer = d
        }

        override fun onError(e: Throwable) {
            getView().showToast(e.message!!)
        }

        override fun onNext(t: ThreadItem) {
            getView().showThread(t)
        }
    }

    inner class CommentsObserver : Observer<Array<Comment>> {
        lateinit var streamDisposer: Disposable
        override fun onComplete() {
            streamDisposer.dispose()
            this@ThreadPresenterImpl.commentsObserver = null
        }
        override fun onSubscribe(d: Disposable) {
            streamDisposer = d
        }
        override fun onError(e: Throwable) {
            getView().showToast(e.message!!)
        }
        override fun onNext(t: Array<Comment>) {
            getView().showComments(t.asList())
        }
    }


}
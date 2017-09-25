package be.simulan.reddit_demo.thread.presenters

interface ThreadPresenter {
    fun loadThread(id:String)
    fun loadComments(id:String)
}
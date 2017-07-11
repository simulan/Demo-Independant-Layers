package be.simulan.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.di.components.DaggerThreadsComponent
import be.simulan.reddit_demo.di.modules.ThreadsModule
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay
import be.simulan.reddit_demo.mvp.presenters.ThreadsPresenter
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter
import be.simulan.reddit_demo.mvp.views.adapters.scrollers.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_threads.*
import timber.log.Timber
import javax.inject.Inject

class ThreadsActivity constructor() : BaseActivity(), ThreadsView {
    @Inject protected lateinit var presenter: ThreadsPresenter
    @Inject protected lateinit var adapter: ThreadsAdapter
    private lateinit var scrollListener: ThreadsScrollListener

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        initializeList()
        bindEvents()
        presenter.getThreads()
        Timber.d("${this.javaClass}'s View Loaded")
    }
    private fun initializeList() {
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        scrollListener = ThreadsScrollListener(layoutManager)
        recycler.adapter = adapter
        recycler.layoutManager = layoutManager as RecyclerView.LayoutManager
        recycler.addOnScrollListener(scrollListener)
    }
    private fun bindEvents() {
        adapter.getThreadClickSubject().subscribe { getThread(it) }
        adapter.getThumbnailClickSubject().subscribe { presenter.getThumbnail( it ) }
    }

    private fun getThread(id : String) {

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.threads_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(presenter)
        searchView.setOnCloseListener(presenter)
        return true
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //Here I will react to user giving permission in webpage (OAuth)
    }

    override fun getContentView(): Int = R.layout.activity_threads
    override fun resolveDaggerDependencies() {
        DaggerThreadsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .threadsModule(ThreadsModule(this))
                .build().inject(this)
    }
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    override fun showThumbnail(thumbnailOverlay: ThumbnailOverlay) {
        container.inflate(R.layout.overlay_thumbnail,true)
    }
    override fun showThreads(threads: List<ThreadItem>) {
        adapter.add(threads)
        Timber.d("${threads.size} threads added to adapter's list(${adapter.itemCount})")
    }
    override fun clearThreads() {
        adapter.clear()
    }

    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : EndlessScrollListener(linearLayoutManager) {
        override fun onAppendItems(): Boolean {
            Timber.d("scrolled up and requesting more items")
            val thread_prefix = "t3_"
            val limit = 10
            return presenter.getThreads(after = thread_prefix + adapter.getLastId(), limit = limit, count = adapter.itemCount)
        }

    }
}

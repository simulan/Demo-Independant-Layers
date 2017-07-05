package be.simulan.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.di.components.DaggerThreadsComponent
import be.simulan.reddit_demo.di.modules.ThreadsModule
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.presenters.MainPresenter
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter
import be.simulan.reddit_demo.mvp.views.adapters.scrollers.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class ThreadsActivity constructor() : BaseActivity(), ThreadsView {
    @Inject protected lateinit var mPresenter: MainPresenter
    @Inject protected lateinit var mAdapter: ThreadsAdapter
    private lateinit var scrollListener: ThreadsScrollListener

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        scrollListener = ThreadsScrollListener(layoutManager)
        recycler.adapter = mAdapter
        recycler.layoutManager = layoutManager
        recycler.addOnScrollListener(scrollListener)
        mPresenter.getThreads()


        Timber.d("${this.javaClass}'s View Loaded")
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.threads_menu, menu)
        val searchItem = menu!!.findItem(R.id.action_search)
        val searchView = MenuItemCompat.getActionView(searchItem) as SearchView
        searchView.setOnQueryTextListener(mPresenter)
        searchView.setOnCloseListener(mPresenter)
        return true
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //Here I will react to user giving permission in webpage (OAuth)
    }

    override fun showThreads(threads: List<RThread>) {
        Timber.d("${threads.size} threads added to adapter's list(${mAdapter.itemCount})")
        mAdapter.add(threads)
        if (threads.isEmpty()) {
            Timber.d(".ThreadScrollListener blocking appends now")
        }
    }
    override fun clearThreads() {
        mAdapter.clear()
    }

    override fun getContentView(): Int = R.layout.activity_main
    override fun resolveDaggerDependencies() {
        DaggerThreadsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .threadsModule(ThreadsModule(this))
                .build().inject(this)
    }
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : EndlessScrollListener(linearLayoutManager) {
        override fun onAppendItems(): Boolean {
            Timber.d("scrolled up and requesting more items")
            val thread_prefix = "t3_"
            val limit = 10
            return mPresenter.getThreads(after = thread_prefix + mAdapter.getLastId(), limit = limit, count = mAdapter.itemCount)
        }

    }
}

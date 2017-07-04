package be.simulan.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.di.components.DaggerMainComponent
import be.simulan.reddit_demo.di.modules.MainModule
import be.simulan.reddit_demo.mvp.models.data.RThread
import be.simulan.reddit_demo.mvp.presenters.MainPresenter
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter
import be.simulan.reddit_demo.mvp.views.adapters.scrollers.EndlessScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity constructor() : BaseActivity(), MainView {
    @Inject protected lateinit var mPresenter: MainPresenter
    @Inject protected lateinit var mAdapter: ThreadsAdapter
    private lateinit var scrollListener: ThreadsScrollListener
    private val THREAD_PREFIX = "t3_"
    private val LIMIT = 10
    var calledOnViewReady = false

    //LC
    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        calledOnViewReady =true
        super.onViewReady(savedInstanceState, intent)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        scrollListener = ThreadsScrollListener(layoutManager)
        recycler.adapter = mAdapter
        recycler.layoutManager = layoutManager
        mPresenter.getThreads()
        recycler.addOnScrollListener(scrollListener)

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

    //LCE
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //Here I will react to user giving permission in webpage (OAuth)
    }

    //MainView
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

    //BaseActivity
    override fun getContentView(): Int = R.layout.activity_main

    override fun resolveDaggerDependencies() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainModule(MainModule(this))
                .build().inject(this)
    }

    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

    //ScrollListener
    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : EndlessScrollListener(linearLayoutManager) {

        override fun onAppendItems(): Boolean {
            //blockingPrepend= false
            Timber.d("scrolled up and requesting more items")
            return mPresenter.getThreads(after = THREAD_PREFIX + mAdapter.getLastId(), limit = LIMIT, count = mAdapter.itemCount)
        }

    }
}

package be.sanderdebleecker.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.widget.Toast
import be.sanderdebleecker.reddit_demo.R
import be.sanderdebleecker.reddit_demo.di.components.DaggerMainComponent
import be.sanderdebleecker.reddit_demo.di.modules.MainModule
import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import be.sanderdebleecker.reddit_demo.mvp.presenters.MainPresenter
import be.sanderdebleecker.reddit_demo.mvp.views.adapters.ThreadsAdapter
import be.sanderdebleecker.reddit_demo.mvp.views.adapters.scrollers.ScrollListener
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity constructor() : BaseActivity(), MainView {
    @Inject protected lateinit var mPresenter: MainPresenter
    @Inject protected lateinit var mAdapter: ThreadsAdapter
    private lateinit var scrollListener : ThreadsScrollListener
    private val THREAD_PREFIX = "t3_"
    private val LIMIT = 10

    //LC
    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        scrollListener = ThreadsScrollListener(layoutManager)
        recycler.adapter = mAdapter
        recycler.layoutManager = layoutManager
        mPresenter.getThreads()
        recycler.addOnScrollListener(scrollListener)
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
    override fun showThreads(threads: List<RThread>, append: Boolean) {
        if(append) {
            mAdapter.add(threads)
            if(threads.isEmpty()) {
                scrollListener.isBlockingAppend = true
                println("")
            }
        } else {
            mAdapter.prepend(threads)
            if(threads.isEmpty()) {
                scrollListener.isBlockingPrepend = true
            }
        }
    }
    override fun clearThreads() {
        mAdapter.clear()
        scrollListener.isBlockingAppend = false
        scrollListener.isBlockingPrepend = false
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
    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : ScrollListener(linearLayoutManager) {
        override var isBlockingAppend: Boolean = false
        override var isBlockingPrepend: Boolean = true

        override fun onAppendItems() : Boolean {
            //isBlockingPrepend= false
            return mPresenter.getThreads(after=THREAD_PREFIX+mAdapter.lastId,limit=LIMIT,count=mAdapter.itemCount)
        }

        override fun onPrependItems(): Boolean {
            //isBlockingAppend= false
            return mPresenter.getThreads(before=THREAD_PREFIX+mAdapter.firstId,limit=LIMIT,count=mAdapter.itemCount)
        }
    }
}

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
import be.simulan.reddit_demo.mvp.models.data.ThumbnailOverlay
import be.simulan.reddit_demo.mvp.presenters.ThreadsPresenterImpl
import be.simulan.reddit_demo.mvp.views.adapters.scrollers.EndlessScrollListener
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_threads.*
import kotlinx.android.synthetic.main.overlay_thumbnail.*
import timber.log.Timber
import javax.inject.Inject

class ThreadsActivity constructor() : BaseActivity(), ThreadsView {
    @Inject protected lateinit var presenter: ThreadsPresenterImpl
    private lateinit var scrollListener: ThreadsScrollListener

    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        initializeRecyclerView()
        presenter.loadThreads()
        Timber.d("${this.javaClass}'s View Loaded")
    }
    private fun initializeRecyclerView() {
        val layoutManager: LinearLayoutManager = LinearLayoutManager(this)
        scrollListener = ThreadsScrollListener(layoutManager)
        recyclerview.adapter = presenter.getAdapter()
        recyclerview.layoutManager = layoutManager as RecyclerView.LayoutManager
        recyclerview.addOnScrollListener(scrollListener)
        presenter.bindAdapter()
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
        txtv_title.text = thumbnailOverlay.title
        Picasso.with(this).load(thumbnailOverlay.thumbnail.url)
                .placeholder(R.raw.placeholder)
                .fit().centerCrop().into(imgv_thumbnail)
    }
    override fun showThread(id: String) {}

    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : EndlessScrollListener(linearLayoutManager) {
        override fun onAppendItems(): Boolean {
            Timber.d("scrolled up and requesting more items")
            return presenter.loadThreads()
        }

    }
}

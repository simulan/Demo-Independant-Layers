package be.simulan.reddit_demo.mvp.views

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Point
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.view.MenuItemCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.di.components.DaggerThreadsComponent
import be.simulan.reddit_demo.di.modules.ThreadsModule
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import be.simulan.reddit_demo.mvp.presenters.ThreadsPresenterImpl
import be.simulan.reddit_demo.mvp.views.adapters.scrollers.EndlessScrollListener
import com.davemorrissey.labs.subscaleview.ImageSource
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_threads.*
import timber.log.Timber
import javax.inject.Inject

class ThreadsActivity constructor() : BaseActivity(), ThreadsView {
    val EXTRA_ID = "${this.javaClass}.Id"
    @Inject lateinit internal var presenter: ThreadsPresenterImpl
    private lateinit var scrollListener: ThreadsScrollListener
    private var state : State = State.READY

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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(state==State.SHOWING_THUMBNAIL) {
            val thumbnail : View = container.findViewById(R.id.containerContent_thumbnail)
            if(!thumbnail.pointWithin(Point(ev!!.rawX.toInt(), ev.rawY.toInt()))) {
                hideThumbnail()
                return true
            }
        }else{
            return super.dispatchTouchEvent(ev)
        }
        return false
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

    override fun showThumbnail(thumbnailItem: ThumbnailItem) {
        if(state==State.READY) {
            state=State.SHOWING_THUMBNAIL
            val view : View = container.inflate(R.layout.view_thumbnail,true)
            loadThumbnail(view,thumbnailItem)
        }
    }
    private fun loadThumbnail(container: View, item : ThumbnailItem) {
        val title = container.findViewById(R.id.txtv_thumbnail_title) as TextView
        val imageView = container.findViewById(R.id.imgv_thumbnail) as SubsamplingScaleImageView
        title.text = item.title
        val target = object : com.squareup.picasso.Target {
            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
            override fun onBitmapFailed(errorDrawable: Drawable?) {
                hideThumbnail()
                Toast.makeText(this@ThreadsActivity,"Could not show image", Toast.LENGTH_SHORT).show()
            }
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) = imageView.setImage(ImageSource.bitmap(bitmap))
        }
        container.post {
            Picasso.with(this@ThreadsActivity).load(item.thumbnail.url).into(target)
        }
    }
    fun hideThumbnail() {
        state = State.READY
        container.removeView(findViewById(R.id.container_thumbnail))
    }
    override fun showThread(id: String) {
        startActivity(Intent(this,ThreadActivity::class.java)
                .putExtra(EXTRA_ID,id))
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

    inner class ThreadsScrollListener(linearLayoutManager: LinearLayoutManager) : EndlessScrollListener(linearLayoutManager) {
        override fun onAppendItems(): Boolean {
            Timber.d("scrolled up and requesting more items")
            return presenter.loadThreads()
        }
    }
    private enum class State {
        READY,
        SHOWING_THUMBNAIL
    }
    private fun View.pointWithin(point : Point) : Boolean {
        var circumference : Rect = Rect()
        this.getGlobalVisibleRect(circumference)
        return circumference.contains(point.x,point.y)
    }
}

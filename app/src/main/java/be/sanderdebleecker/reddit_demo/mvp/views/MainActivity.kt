package be.sanderdebleecker.reddit_demo.mvp.views

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import be.sanderdebleecker.reddit_demo.R
import be.sanderdebleecker.reddit_demo.di.components.DaggerMainComponent
import be.sanderdebleecker.reddit_demo.di.modules.MainModule
import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
import be.sanderdebleecker.reddit_demo.mvp.presenters.MainPresenter
import be.sanderdebleecker.reddit_demo.mvp.views.adapters.ScrollListener
import be.sanderdebleecker.reddit_demo.mvp.views.adapters.ThreadsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


open class MainActivity constructor() : BaseActivity(), MainView {
    @Inject protected lateinit var mPresenter: MainPresenter
    private var mAdapter : ThreadsAdapter = ThreadsAdapter()
    //LC
    override fun onViewReady(savedInstanceState: Bundle?, intent: Intent) {
        super.onViewReady(savedInstanceState, intent)
        val layoutManager : LinearLayoutManager = LinearLayoutManager(this)
        recycler.adapter = mAdapter
        recycler.layoutManager = layoutManager
        mPresenter.getThreads()
        recycler.addOnScrollListener(  object : ScrollListener(layoutManager) {
            val limit = 10
            override fun onLoadMore(page: Int, totalItemCount: Int): Boolean {
                //invert result of mPresenter, as we can't load when already loading
                return !mPresenter.getThreads("t3_"+mAdapter.items.last().id,limit,mAdapter.items.size)
            }
        })
        //optional : subscribe item.onclick to presenter
    }

    //LCE
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        //Here I will react to user giving permission in webpage (OAuth)
    }

    //BaseActivity
    override fun getContentView(): Int = R.layout.activity_main
    override fun resolveDaggerDependencies() {
        DaggerMainComponent.builder()
                .applicationComponent(getApplicationComponent())
                .mainModule(MainModule(this))
                .build().inject(this)
    }

    //I MainView
    override fun showThreads(threads: MutableList<RThread>) {
        mAdapter.items = (mAdapter.items + threads) as MutableList<RThread>
        mAdapter.notifyDataSetChanged()
    }
    override fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
    }

}

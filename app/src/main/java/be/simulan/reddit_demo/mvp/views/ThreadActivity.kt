package be.simulan.reddit_demo.mvp.views

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.di.components.DaggerThreadComponent
import be.simulan.reddit_demo.di.modules.ThreadModule
import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.presenters.ThreadPresenterImpl
import be.simulan.reddit_demo.mvp.views.ThreadsActivity.Companion.EXTRA_THREAD_ID
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_thread.*
import javax.inject.Inject

val INSET_TYPE_KEY = "inset_type"
val INSET = "inset"

class ThreadActivity @Inject constructor() : BaseActivity(),ThreadView {
    @Inject lateinit internal var presenter: ThreadPresenterImpl
    private val groupAdapter: GroupAdapter<ViewHolder> = GroupAdapter()
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        initialize()
        load()
    }
    private fun initialize() {
        gridLayoutManager = GridLayoutManager(this, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }
        recyclerview.apply {
            layoutManager=gridLayoutManager
            adapter=groupAdapter
        }
    }
    private fun load() {
        val id: String = intent.getStringExtra(EXTRA_THREAD_ID)
        presenter.loadThread(id)
        presenter.loadComments(id)
    }
    override fun showThread(thread: ThreadItem) {
        txtvTitle.text = thread.title
        txtvAuthor.text = thread.author
    }
    override fun showComments(comments: List<Comment>) {

    }

    override fun resolveDaggerDependencies() {
        DaggerThreadComponent.builder()
                .applicationComponent(getApplicationComponent())
                .threadModule(ThreadModule(this))
                .build().inject(this)
    }
    override fun getContentView(): Int = R.layout.activity_thread
    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}

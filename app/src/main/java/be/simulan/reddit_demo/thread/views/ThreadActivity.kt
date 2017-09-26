package be.simulan.reddit_demo.thread.views

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.application.views.BaseActivity
import be.simulan.reddit_demo.comments.models.Comment
import be.simulan.reddit_demo.comments.views.ExpandableCommentGroup
import be.simulan.reddit_demo.thread.DaggerThreadComponent
import be.simulan.reddit_demo.thread.ThreadModule
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.thread.presenters.ThreadPresenterImpl
import be.simulan.reddit_demo.thread.views.ThreadsActivity.Companion.EXTRA_THREAD_ID
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_thread.*
import javax.inject.Inject

val INSET_TYPE_KEY = "inset_type"
val INSET = "inset"

class ThreadActivity @Inject constructor() : BaseActivity(), ThreadView {
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
        groupAdapter.addAll(List(comments.size) {
            index ->
            ExpandableCommentGroup(comments[index])
        })
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

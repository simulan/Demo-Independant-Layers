package be.simulan.reddit_demo.mvp.views

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.widget.Toast
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.models.data.Comment
import be.simulan.reddit_demo.mvp.models.data.ExpandableCommentGroup
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_threads.*

val INSET_TYPE_KEY = "inset_type"
val INSET = "inset"

class ThreadActivity : BaseActivity(),ThreadView {
    private val groupAdapter: GroupAdapter<ViewHolder> = GroupAdapter()
    private lateinit var gridLayoutManager: GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thread)
        initializeList()
    }
    private fun initializeList() {
        gridLayoutManager = GridLayoutManager(this, groupAdapter.spanCount).apply {
            spanSizeLookup = groupAdapter.spanSizeLookup
        }
        recyclerview.apply {
            layoutManager=gridLayoutManager
            adapter=groupAdapter
        }
    }
    override fun showThread(thread: ThreadItem) {

    }
    override fun showComments(comments: List<Comment>) {

    }
    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}

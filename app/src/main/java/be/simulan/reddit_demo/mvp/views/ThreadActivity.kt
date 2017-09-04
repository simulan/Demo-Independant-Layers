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
        populateList()
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
    private fun populateList() {
        var lvl3comment = Comment(arrayListOf())
        lvl3comment.author = "Charle"
        lvl3comment.text = "C'est faux"
        var lvl3comment2 = Comment(arrayListOf())
        lvl3comment2.author = "MJL"
        lvl3comment2.text = "this ^"
        var lvl2comment = Comment(arrayListOf(lvl3comment,lvl3comment2))
        lvl2comment.author = "TheGreatEscalade"
        lvl2comment.text = "It's because it's depicted as such"
        var lvl2comment2 = Comment(arrayListOf())
        lvl2comment2.author = "Loldrol"
        lvl2comment2.text = "roflcopter"
        var lvl1comment = Comment(arrayListOf(lvl2comment,lvl2comment2))
        lvl1comment.author = "PizzaFThis"
        lvl1comment.text = "This seems to be a conspiracy"
        var expandableGroup = ExpandableCommentGroup(lvl1comment)
        groupAdapter.add(expandableGroup)
    }
    override fun showThread(thread: ThreadItem) {

    }
    override fun showComments(comments: List<Comment>) {

    }
    override fun showToast(msg: String) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
    }
}

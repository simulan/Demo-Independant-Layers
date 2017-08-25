package be.simulan.reddit_demo.mvp.views

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.FullBleedCardItem
import be.simulan.reddit_demo.mvp.views.adapters.viewholders.HeaderItem
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.activity_threads.*

val INSET_TYPE_KEY = "inset_type"
val INSET = "inset"

class ThreadActivity : AppCompatActivity() {
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
        val fullBleedItemSection  = Section(HeaderItem(R.string.comments_section))
        val item = FullBleedCardItem(R.color.colorBlueBG)
        item.text="Text here"
        fullBleedItemSection.add(item)
        groupAdapter.add(fullBleedItemSection)
    }
}

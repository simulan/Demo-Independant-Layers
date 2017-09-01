package be.simulan.reddit_demo.mvp.models.data

import android.view.LayoutInflater
import android.view.View
import be.simulan.reddit_demo.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_header_item.view.*

class ExpandableCommentItem(private val comment : Comment, private val depth : Int) : Item<ViewHolder>(), ExpandableItem {
    lateinit var toggleListener : ExpandableGroup

    override fun bind(viewHolder: ViewHolder, position: Int) {
        addInDepthViews(viewHolder)

        viewHolder.itemView.title.text = comment.author
        viewHolder.itemView.subtitle.text = comment.text
        viewHolder.itemView.apply {
            setOnLongClickListener({
                    toggleListener.onToggleExpanded()
                    true
            })
        }
    }
    private fun addInDepthViews(viewHolder: ViewHolder) {
        val container = viewHolder.itemView.seperatorContainer
        container.removeAllViews()
        container.visibility = if(depth>0) {
            View.VISIBLE
        }else{
            View.GONE
        }
        for(i in 1..depth) {
            val v : View = LayoutInflater.from(viewHolder.itemView.context)
                    .inflate(R.layout.seperator_view, container,false)
            container.addView(v)
        }
    }
    override fun getLayout(): Int = R.layout.row_header_item

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.toggleListener=onToggleListener
    }
}
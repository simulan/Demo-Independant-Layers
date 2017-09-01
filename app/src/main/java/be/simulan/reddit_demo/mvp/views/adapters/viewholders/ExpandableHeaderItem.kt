package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.support.annotation.StringRes
import android.view.View
import be.simulan.reddit_demo.R
import com.xwray.groupie.ExpandableGroup
import com.xwray.groupie.ExpandableItem
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_header.view.*

class ExpandableHeaderItem(@StringRes titleStringResId: Int, @StringRes subtitleResId: Int) : HeaderItem(titleStringResId, subtitleResId), ExpandableItem {

    private lateinit var expandableGroup: ExpandableGroup

    override fun bind(viewHolder: ViewHolder, position: Int) {
        super.bind(viewHolder, position)

        // Initial icon state -- not animated.
        viewHolder.itemView.icon.apply {
            visibility = View.VISIBLE
            setImageResource(if (expandableGroup.isExpanded) R.drawable.ic_back else R.drawable.progress_animation)
            setOnClickListener {
                expandableGroup.onToggleExpanded()
                bindIcon(viewHolder)
            }
        }
    }
    //Animation used here
    private fun bindIcon(viewHolder: ViewHolder) {
        viewHolder.itemView.icon.apply {
            visibility = View.VISIBLE
            setImageResource(if (expandableGroup.isExpanded) R.drawable.ic_progress else R.drawable.ic_progress)
            //(drawable as Animatable).start()
        }
    }

    override fun setExpandableGroup(onToggleListener: ExpandableGroup) {
        this.expandableGroup = onToggleListener
    }
}
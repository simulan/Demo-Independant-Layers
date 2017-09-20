package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.support.annotation.DrawableRes
import android.support.annotation.StringRes
import android.view.View
import be.simulan.reddit_demo.R
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.row_header_item.view.*

open class HeaderItem @JvmOverloads constructor(
        @param:StringRes private val titleStringResId: Int,
        @param:StringRes private val subtitleResId: Int = 0,
        @param:DrawableRes private val iconResId: Int = 0,
        private val onIconClickListener: View.OnClickListener? = null) : Item<ViewHolder>() {

    override fun getLayout(): Int {
        return R.layout.row_header_item
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.itemView.author.setText(titleStringResId)

        viewHolder.itemView.text.apply {
            visibility = if (subtitleResId > 0) View.VISIBLE else View.GONE
            if (subtitleResId > 0) {
                setText(subtitleResId)
            }
        }

    }
}
package be.simulan.reddit_demo.mvp.views.adapters.viewholders

import android.support.annotation.ColorInt
import be.simulan.reddit_demo.R
import be.simulan.reddit_demo.mvp.views.INSET
import be.simulan.reddit_demo.mvp.views.INSET_TYPE_KEY
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.android.synthetic.main.item_card.view.*

open class CardItem @JvmOverloads constructor(@param:ColorInt private val colorRes: Int, var text: CharSequence? = "") : Item<ViewHolder>() {

    init {
        extras.put(INSET_TYPE_KEY, INSET)
    }

    override fun getLayout(): Int {
        return R.layout.item_card
    }

    override fun bind(viewHolder: ViewHolder, position: Int) {
        //viewBinding.getRoot().setBackgroundColor(colorRes);
        viewHolder.itemView.text.text = text
    }
}
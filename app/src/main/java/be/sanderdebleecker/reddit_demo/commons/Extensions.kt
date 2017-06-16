package be.sanderdebleecker.reddit_demo.commons

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * @author Simulan
 * @version 1.0.0
 * @since 16/06/2017
 */

fun ViewGroup.inflate(layoutId: Int, attachToRoot : Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId,this,attachToRoot)
}

/*fun ImageView.loadImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}*/
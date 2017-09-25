package be.simulan.reddit_demo

import android.graphics.Point
import android.graphics.Rect
import android.view.View

fun View.pointWithin(point : Point) : Boolean {
    var circumference : Rect = Rect()
    this.getGlobalVisibleRect(circumference)
    return circumference.contains(point.x,point.y)
}


package be.simulan.reddit_demo.mvp.presenters

import be.simulan.reddit_demo.mvp.models.data.ThreadItem

interface ThreadsProvider {
    fun getItemCount() : Int
    fun getItem(position : Int) : ThreadItem
}
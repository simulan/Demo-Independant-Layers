package be.simulan.reddit_demo.thread.presenters

import be.simulan.reddit_demo.thread.models.ThreadItem

interface ThreadsProvider {
    fun getItemCount() : Int
    fun getItem(position : Int) : ThreadItem
}
package be.simulan.reddit_demo.thread.presenters

import be.simulan.reddit_demo.thread.models.Category
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.thread.views.ThreadsAdapter

interface ThreadsPresenter {
    fun getAdapter() : ThreadsAdapter
    fun bindAdapter()

    fun getItem(position : Int) : ThreadItem
    fun getItemCount() : Int

    fun loadThreads(): Boolean
    fun switchCategory(newCategory: Category)
}
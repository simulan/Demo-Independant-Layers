package be.simulan.reddit_demo.mvp.presenters

import be.simulan.reddit_demo.mvp.models.data.Category
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter

interface ThreadsPresenter {
    fun getAdapter() : ThreadsAdapter
    fun bindAdapter()

    fun getItem(position : Int) : ThreadItem
    fun getItemCount() : Int

    fun loadThreads(): Boolean
    fun switchCategory(newCategory: Category)
}
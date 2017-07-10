package be.simulan.reddit_demo.mvp.views.adapters

import be.simulan.reddit_demo.mvp.models.data.ThreadItem

/**
 * @author Simulan
 * @version 1.0.0
 * @since 02/07/2017
 */
interface ThreadsArrayList {
    fun add(additions : List<ThreadItem>)
    fun clear()
    fun getLastId() : String
}
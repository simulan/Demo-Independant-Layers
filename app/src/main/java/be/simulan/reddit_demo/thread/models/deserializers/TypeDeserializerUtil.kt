package be.simulan.reddit_demo.thread.models.deserializers

import be.simulan.reddit_demo.thread.models.ThreadItem

fun getType(url : String) : ThreadItem.Type {
    if(url.contains(".jpg") || url.contains(".png") || url.contains(".gif")) {
        return ThreadItem.Type.Image
    }else if(url.contains("reddit")) {
        return ThreadItem.Type.Post
    }else {
        return ThreadItem.Type.Link
    }
}
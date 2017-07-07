package be.simulan.reddit_demo.mvp.models.data

data class ThreadComment(val id : String,val author : String,val text : String,val parent : String,val score : Long)
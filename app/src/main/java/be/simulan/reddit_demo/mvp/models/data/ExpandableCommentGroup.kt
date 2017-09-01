package be.simulan.reddit_demo.mvp.models.data

import com.xwray.groupie.ExpandableGroup

class ExpandableCommentGroup constructor(comment : Comment,depth: Int=0) : ExpandableGroup(ExpandableCommentItem(comment,depth)) {
    init{
        for(comm in comment.childs) {
            val newDepth : Int = depth.plus(1)
            add(ExpandableCommentGroup(comm,newDepth))
        }
    }
}
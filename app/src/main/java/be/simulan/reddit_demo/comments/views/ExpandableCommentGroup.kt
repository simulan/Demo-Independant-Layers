package be.simulan.reddit_demo.comments.views

import be.simulan.reddit_demo.comments.models.Comment
import com.xwray.groupie.ExpandableGroup

class ExpandableCommentGroup constructor(comment : Comment, depth: Int=0) : ExpandableGroup(ExpandableCommentItem(comment, depth)) {
    init{
        for(comm in comment.childs) {
            val newDepth : Int = depth.plus(1)
            add(ExpandableCommentGroup(comm, newDepth))
        }
    }
}
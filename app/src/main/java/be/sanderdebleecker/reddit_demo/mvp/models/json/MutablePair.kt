package be.sanderdebleecker.reddit_demo.mvp.models.json

import java.io.Serializable

/**
 * @author Simulan
 * @version 1.0.0
 * @since 14/06/2017
 */
data class MutablePair<A, B> constructor(var first: A, var second: B) : Serializable {
    override fun toString(): String = "($first, $second)"
}
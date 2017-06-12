package be.sanderdebleecker.reddit_demo.da.apis

import be.sanderdebleecker.reddit_demo.mvp.models.ThreadsEnvelope
import io.reactivex.Observable
import retrofit2.http.GET

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */
interface IRedditService {
    @GET("/r/androiddev/new.json")
    fun listPosts() : Observable<ThreadsEnvelope>
}
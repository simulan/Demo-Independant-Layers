package be.sanderdebleecker.reddit_demo.da.apis

import be.sanderdebleecker.reddit_demo.mvp.models.json.ThreadsEnvelope
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */
//reddit fullname = type prefix + id
//e.g t3_bfi0
interface IRedditService {
    @GET("/r/{sub}/new.json?")
    fun listPosts(@Path("sub") sub : String = "androiddev",
                  @Query("after") after : String = "",
                  @Query("limit") limit : Int = 20,
                  @Query("count") count : Int = 1) : Observable<ThreadsEnvelope>
}
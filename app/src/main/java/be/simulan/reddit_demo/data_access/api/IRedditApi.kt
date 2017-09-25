package be.simulan.reddit_demo.data_access.api

import be.simulan.reddit_demo.comments.models.Comment
import be.simulan.reddit_demo.thread.models.ThreadItem
import be.simulan.reddit_demo.thread.models.ThumbnailItem
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IRedditApi {
    @GET("/r/{sub}/new.json?")
    fun listThreads(@Path("sub") sub : String = "androiddev",
                    @Query("after") after : String,
                    @Query("limit") limit : Int,
                    @Query("count") count : Int) : Observable<Array<ThreadItem>>
    @GET("/r/{sub}/search.json?")
    fun searchThreads(@Path("sub") sub : String = "androiddev",
                      @Query("q") q : String,
                      @Query("restrict_sr") restrict_sr : Boolean,
                      @Query("after") after : String,
                      @Query("limit") limit : Int=1,
                      @Query("count") count : Int) : Observable<Array<ThreadItem>>
    @GET("/by_id/{id}.json?")
    fun getThreadById(@Path("id") id : String) : Observable<ThreadItem>
    @GET("/by_id/{id}.json?")
    fun getThumbnailById(@Path("id") id : String) : Observable<ThumbnailItem>
    @GET("/r/{sub}/comments/{id}/comments.json?") @BoxedArray
    fun listComments(@Path("sub") sub : String = "androiddev",
                    @Path("id") id : String) : Observable<Array<Comment>>

}

annotation class BoxedArray

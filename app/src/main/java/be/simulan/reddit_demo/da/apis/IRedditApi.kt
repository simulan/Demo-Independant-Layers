package be.simulan.reddit_demo.da.apis

import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
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
    fun getThumbnailById(@Path("id") id : String) : Observable<ThumbnailItem>
    @GET("/r/{sub}/comments/{id}/comments.json?")
    fun listComments(@Path("sub") sub : String = "androiddev",
                    @Path("id") after : String)
}
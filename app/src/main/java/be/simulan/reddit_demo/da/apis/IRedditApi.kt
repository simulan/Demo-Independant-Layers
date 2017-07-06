package be.simulan.reddit_demo.da.apis

import be.simulan.reddit_demo.mvp.models.data.ThreadHeader
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

//Threads ID is prefixed by t3_

interface IRedditApi {
    @GET("/r/{sub}/new.json?")
    fun listThreads(@Path("sub") sub : String = "androiddev",
                    @Query("after") after : String,
                    @Query("limit") limit : Int,
                    @Query("count") count : Int) : Observable<Array<ThreadHeader>>
    @GET("/r/{sub}/search.json?")
    fun searchThreads(@Path("sub") sub : String = "androiddev",
                      @Query("q") q : String,
                      @Query("restrict_sr") restrict_sr : Boolean,
                      @Query("after") after : String,
                      @Query("limit") limit : Int=1,
                      @Query("count") count : Int) : Observable<Array<ThreadHeader>>
}
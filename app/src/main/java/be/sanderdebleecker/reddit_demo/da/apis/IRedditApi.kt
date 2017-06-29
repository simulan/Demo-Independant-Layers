package be.sanderdebleecker.reddit_demo.da.apis

import be.sanderdebleecker.reddit_demo.mvp.models.data.RThread
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
interface IRedditApi {
    @GET("/r/{sub}/new.json?")
    fun listThreads(@Path("sub") sub : String = "androiddev",
                    @Query("after") after : String,
                    @Query("limit") limit : Int,
                    @Query("count") count : Int) : Observable<Array<RThread>>
    @GET("/r/{sub}/new.json?")
    fun listThreadsBefore(@Path("sub") sub : String = "androiddev",
                    @Query("before") before : String,
                    @Query("limit") limit : Int,
                    @Query("count") count : Int) : Observable<Array<RThread>>
    @GET("/r/{sub}/search.json?")
    fun searchThreads(@Path("sub") sub : String = "androiddev",
                      @Query("q") q : String,
                      @Query("restrict_sr") restrict_sr : Boolean,
                      @Query("after") after : String,
                      @Query("limit") limit : Int=1,
                      @Query("count") count : Int) : Observable<Array<RThread>>
}
package be.simulan.reddit_demo.data_access.authentication

import io.reactivex.Observable

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
interface IOAuthenticator {
    fun getAccessToken() : Observable<Pair<String, String>>
}
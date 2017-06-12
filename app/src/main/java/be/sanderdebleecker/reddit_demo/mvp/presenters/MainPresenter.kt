package be.sanderdebleecker.reddit_demo.mvp.presenters

import android.content.Intent
import android.net.Uri
import android.util.Base64
import android.util.Log
import be.sanderdebleecker.reddit_demo.mvp.views.MainView
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */

class MainPresenter @Inject constructor() : BasePresenter<MainView>() {


    // nonview logic
    fun redditLogin() {
        val url = String.format(AUTH_URL, CLIENT_ID, STATE, REDIRECT_URI)
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
    fun redditLoginResult(intent: Intent) {
        if (intent.action == Intent.ACTION_VIEW) {
            val uri = intent.data
            if (uri.getQueryParameter("error") != null) {
                val error = uri.getQueryParameter("error")
                Log.e(this.packageName, "An error has occurred : " + error)
            } else {
                val state = uri.getQueryParameter("state")
                if (state == STATE) {
                    val code = uri.getQueryParameter("code")
                    getAccessToken(code)
                }
            }
        }
    }

}
package be.sanderdebleecker.reddit_demo.da

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */
/*class OAuthenticator : IOAuthenticator {


    override fun getAccessToken(): Observable<Pair<String, String>> {
        val client = OkHttpClient()
        val authString = CLIENT_ID + ":"
        val encodedAuthString = Base64.encodeToString(authString.toByteArray(),
                Base64.NO_WRAP)
        val request = Request.Builder()
                .addHeader("User-Agent", "Sample App")
                .addHeader("Authorization", "Basic " + encodedAuthString)
                .url(ACCESS_TOKEN_URL)
                .post(RequestBody.create(MediaType.parse("application/x-www-form-urlencoded"),
                        "grant_type=authorization_code&code=" + code +
                                "&redirect_uri=" + REDIRECT_URI))
                .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(this@OAuthenticator.packageName, "An error has occurred : " + e)
            }

            override fun onResponse(call: Call, response: Response) {
                val json = response.body()?.string()

                var data: JSONObject? = null
                try {
                    data = JSONObject(json)
                    val accessToken = data.optString("access_token")
                    val refreshToken = data.optString("refresh_token")
                    Log.d(this@OAuthenticator.toString(), "Access Token = " + accessToken)
                    Log.d(this@OAuthenticator.toString(), "Refresh Token = " + refreshToken)
                } catch (e: JSONException) {
                    e.printStackTrace()
                }

            }
        })
        return object : Observable<Pair<String, String>>() {
            override fun subscribeActual(observer: Observer<in Pair<String, String>>?) {
                TODO("not implemented")
            }
        }
    }
}*/
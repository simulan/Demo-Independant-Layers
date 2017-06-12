package be.sanderdebleecker.reddit_demo.di.modules

import android.content.Context
import be.sanderdebleecker.reddit_demo.BuildConfig
import dagger.Module
import dagger.Provides
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.net.URL
import javax.inject.Singleton

/**
 * @author Simulan
 * *
 * @version 1.0.0
 * *
 * @since 11/06/2017
 */
@Module
class ApplicationModule(private val mContext: Context) {
    private val APP_KEY = BuildConfig.APP_KEY
    private val URL : String = "http://www.example.com"

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }

    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        //Configure to deserialize returned JSON
        val clientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()
        //Interceptor to add API key to every request
        clientBuilder.addInterceptor { chain ->
            val original: Request = chain!!.request()
            val originalHttpUrl: HttpUrl = original.url()
            val url: HttpUrl = originalHttpUrl.newBuilder()
                    .addQueryParameter("key", APP_KEY)
                    .build()
            val requestBuilder: Request.Builder = original.newBuilder().url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        }
        return clientBuilder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient, converterFactory: GsonConverterFactory, adapterFactory: RxJava2CallAdapterFactory): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(URL)
                .addConverterFactory(converterFactory)
                .addCallAdapterFactory(adapterFactory)
                .build();
    }

}

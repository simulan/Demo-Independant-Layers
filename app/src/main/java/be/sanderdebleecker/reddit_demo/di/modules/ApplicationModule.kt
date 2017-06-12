package be.sanderdebleecker.reddit_demo.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
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
                .build()
    }

}

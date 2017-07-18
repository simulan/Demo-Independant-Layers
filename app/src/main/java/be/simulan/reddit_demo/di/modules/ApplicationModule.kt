package be.simulan.reddit_demo.di.modules

import android.content.Context
import be.simulan.reddit_demo.mvp.models.data.ThreadItem
import be.simulan.reddit_demo.mvp.models.data.ThumbnailItem
import be.simulan.reddit_demo.mvp.models.type_adapters.RThreadCollectionDeserializer
import be.simulan.reddit_demo.mvp.models.type_adapters.ThumbnailOverlayDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
@Module
class ApplicationModule(private val mContext: Context) {
    private val URL : String = "https://www.reddit.com"

    @Provides
    @Singleton
    internal fun provideContext(): Context {
        return mContext
    }
    @Singleton
    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory {
        val gson : Gson = GsonBuilder()
                .registerTypeAdapter(Array<ThreadItem>::class.java,RThreadCollectionDeserializer())
                .registerTypeAdapter(ThumbnailItem::class.java,ThumbnailOverlayDeserializer())
                .create()
        return GsonConverterFactory.create(gson)
    }
    @Singleton
    @Provides
    fun provideRxJava2CallAdapterFactory(): RxJava2CallAdapterFactory {
        return RxJava2CallAdapterFactory.create()
    }
    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
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

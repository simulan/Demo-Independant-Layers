package be.simulan.reddit_demo.di.modules

import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.di.scopes.PerActivity
import be.simulan.reddit_demo.mvp.views.MainView
import be.simulan.reddit_demo.mvp.views.adapters.ThreadsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit



/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
@Module
class MainModule(private val mView: MainView) {
    @PerActivity
    @Provides
    internal fun provideView() : MainView {
        return mView
    }

    @PerActivity
    @Provides
    fun provideApiService(retrofit: Retrofit): IRedditApi {
        return retrofit.create<IRedditApi>(IRedditApi::class.java)
    }

    @PerActivity
    @Provides
    fun provideThreadsAdapter() : ThreadsAdapter {
        return ThreadsAdapter()
    }
}
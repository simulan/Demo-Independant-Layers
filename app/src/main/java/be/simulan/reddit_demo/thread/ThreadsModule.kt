package be.simulan.reddit_demo.thread

import be.simulan.reddit_demo.data_access.api.IRedditApi
import be.simulan.reddit_demo.scopes.PerActivity
import be.simulan.reddit_demo.thread.views.ThreadsView
import be.simulan.reddit_demo.thread.views.ThreadsAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ThreadsModule(private val mView: ThreadsView) {
    @PerActivity
    @Provides
    internal fun provideView(): ThreadsView {
        return mView
    }

    @PerActivity
    @Provides
    fun provideApiService(retrofit: Retrofit): IRedditApi {
        return retrofit.create<IRedditApi>(IRedditApi::class.java)
    }

    @PerActivity
    @Provides
    fun provideThreadsAdapter(): ThreadsAdapter {
        return ThreadsAdapter()
    }
}
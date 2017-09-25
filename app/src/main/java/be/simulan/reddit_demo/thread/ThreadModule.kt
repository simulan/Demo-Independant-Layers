package be.simulan.reddit_demo.thread

import be.simulan.reddit_demo.data_access.api.IRedditApi
import be.simulan.reddit_demo.scopes.PerActivity
import be.simulan.reddit_demo.thread.views.ThreadView
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class ThreadModule(private val mView: ThreadView) {
    @PerActivity
    @Provides
    fun provideView() : ThreadView {
        return mView
    }
    @PerActivity
    @Provides
    fun provideApiService(retrofit: Retrofit) : IRedditApi {
        return retrofit.create<IRedditApi>(IRedditApi::class.java)
    }

}
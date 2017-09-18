package be.simulan.reddit_demo.di.modules

import be.simulan.reddit_demo.da.apis.IRedditApi
import be.simulan.reddit_demo.di.scopes.PerActivity
import be.simulan.reddit_demo.mvp.views.ThreadView
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
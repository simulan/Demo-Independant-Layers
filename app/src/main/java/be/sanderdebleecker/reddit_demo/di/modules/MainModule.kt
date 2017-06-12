package be.sanderdebleecker.reddit_demo.di.modules

import be.sanderdebleecker.reddit_demo.di.scopes.PerActivity
import be.sanderdebleecker.reddit_demo.mvp.views.MainView
import dagger.Module
import dagger.Provides

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
}
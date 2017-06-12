package be.sanderdebleecker.reddit_demo

import android.app.Application
import be.sanderdebleecker.reddit_demo.di.components.ApplicationComponent
import be.sanderdebleecker.reddit_demo.di.components.DaggerApplicationComponent
import be.sanderdebleecker.reddit_demo.di.modules.ApplicationModule

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
class RedditApplication : Application() {
    private lateinit var mApplicationComponent: ApplicationComponent;

    override fun onCreate() {
        super.onCreate()
        initializeApplicationComponent()
    }

    private fun initializeApplicationComponent() {
        mApplicationComponent = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    fun getApplicationComponent(): ApplicationComponent {
        return mApplicationComponent
    }
}
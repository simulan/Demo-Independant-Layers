package be.simulan.reddit_demo.application

import android.app.Application
import be.simulan.reddit_demo.BuildConfig
import be.simulan.reddit_demo.application.DaggerApplicationComponent
import timber.log.Timber

open class RedditApplication : Application() {
    private lateinit var mApplicationComponent: ApplicationComponent;

    override fun onCreate() {
        super.onCreate()
        initializeApplicationComponent()
        configTimber()
    }

    private fun configTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree(){
                override fun createStackElementTag(element: StackTraceElement?): String {
                    return super.createStackElementTag(element) + ":" +element?.lineNumber
                }
            })
        } else {
            //TODO: implement release crash reporting
        }
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
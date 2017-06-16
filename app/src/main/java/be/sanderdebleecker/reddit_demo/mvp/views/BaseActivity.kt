package be.sanderdebleecker.reddit_demo.mvp.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import be.sanderdebleecker.reddit_demo.RedditApplication
import be.sanderdebleecker.reddit_demo.di.components.ApplicationComponent

/**
 * @author Simulan
 * @version 1.0.0
 * @since 12/06/2017
 */
open class BaseActivity : AppCompatActivity() {
    //LC
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        setContentView(getContentView())
        onViewReady(savedInstanceState,intent)
    }
    @CallSuper
    protected open fun onViewReady(savedInstanceState: Bundle?,intent: Intent) {
        resolveDaggerDependencies()
    }

    //Application
    protected fun getApplicationComponent(): ApplicationComponent {
        val application: Application = application
        if (application is RedditApplication) {
            return application.getApplicationComponent()
        } else {
            throw Exception("BaseActivity depends on RedditApplication to be defined!")
        }
    }

    //To override
    open fun resolveDaggerDependencies() {
        //can be empty
    }
    open fun getContentView() : Int {
        TODO("implement")
    }
}
package be.simulan.reddit_demo.application.views

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.v7.app.AppCompatActivity
import be.simulan.reddit_demo.application.RedditApplication
import be.simulan.reddit_demo.application.ApplicationComponent

open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState )
        setContentView(getContentView())
        onViewReady(savedInstanceState,intent)
    }
    @CallSuper
    protected open fun onViewReady(savedInstanceState: Bundle?,intent: Intent) {
        resolveDaggerDependencies()
    }
    protected fun getApplicationComponent(): ApplicationComponent {
        val application: Application = application
        if (application is RedditApplication) {
            return application.getApplicationComponent()
        } else {
            throw Exception("BaseActivity depends on RedditApplication to be defined!")
        }
    }

    open fun resolveDaggerDependencies() {
    }
    open fun getContentView() : Int = throw UnsupportedOperationException("Extends BaseActivity, requires getContentView():Int override ")
}
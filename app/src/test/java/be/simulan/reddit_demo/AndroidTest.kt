package be.simulan.reddit_demo

import android.content.Context
import be.simulan.reddit_demo.application.RedditApplication
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import java.io.File

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class,
        application = AndroidTest.ApplicationStub::class,
        sdk = intArrayOf(25))
abstract class AndroidTest {

    fun context(): Context {
        return RuntimeEnvironment.application
    }

    fun cacheDir(): File {
        return context().cacheDir
    }

    internal class ApplicationStub : RedditApplication()
}
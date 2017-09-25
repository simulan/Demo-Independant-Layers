package be.simulan.reddit_demo.application

import android.content.Context
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun exposeContext(): Context
    fun exposeRetrofit(): Retrofit
}
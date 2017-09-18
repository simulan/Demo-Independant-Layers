package be.simulan.reddit_demo.di.components

import android.content.Context
import be.simulan.reddit_demo.di.modules.ApplicationModule
import dagger.Component
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun exposeContext(): Context
    fun exposeRetrofit(): Retrofit
}
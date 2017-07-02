package be.simulan.reddit_demo.di.components

import be.simulan.reddit_demo.di.modules.MainModule
import be.simulan.reddit_demo.di.scopes.PerActivity
import be.simulan.reddit_demo.mvp.views.MainActivity
import dagger.Component

/**
 * @author Simulan
 * @version 1.0.0
 * @since 11/06/2017
 */
@PerActivity
@Component(modules = arrayOf(MainModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface MainComponent {
    fun inject(activity: MainActivity)
}
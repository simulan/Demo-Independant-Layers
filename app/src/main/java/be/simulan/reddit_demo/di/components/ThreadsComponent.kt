package be.simulan.reddit_demo.di.components

import be.simulan.reddit_demo.di.modules.ThreadsModule
import be.simulan.reddit_demo.di.scopes.PerActivity
import be.simulan.reddit_demo.mvp.views.ThreadsActivity
import dagger.Component

@PerActivity
@Component(modules = arrayOf(ThreadsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ThreadsComponent {
    fun inject(activity: ThreadsActivity)
}
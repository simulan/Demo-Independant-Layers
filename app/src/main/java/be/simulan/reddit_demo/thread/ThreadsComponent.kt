package be.simulan.reddit_demo.thread

import be.simulan.reddit_demo.application.ApplicationComponent
import be.simulan.reddit_demo.scopes.PerActivity
import be.simulan.reddit_demo.thread.views.ThreadsActivity
import dagger.Component

@PerActivity
@Component(modules = arrayOf(ThreadsModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface ThreadsComponent {
    fun inject(activity: ThreadsActivity)
}
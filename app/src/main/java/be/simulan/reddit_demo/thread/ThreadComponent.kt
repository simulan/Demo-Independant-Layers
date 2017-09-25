package be.simulan.reddit_demo.thread

import be.simulan.reddit_demo.application.ApplicationComponent
import be.simulan.reddit_demo.scopes.PerActivity
import be.simulan.reddit_demo.thread.views.ThreadActivity
import dagger.Component

    @PerActivity
    @Component(modules = arrayOf(ThreadModule::class), dependencies = arrayOf(ApplicationComponent::class))
    interface ThreadComponent {
        fun inject(activity: ThreadActivity)
    }

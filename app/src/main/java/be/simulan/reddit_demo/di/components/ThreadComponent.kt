package be.simulan.reddit_demo.di.components

import be.simulan.reddit_demo.di.modules.ThreadModule
import be.simulan.reddit_demo.di.scopes.PerActivity
import be.simulan.reddit_demo.mvp.views.ThreadActivity
import dagger.Component

    @PerActivity
    @Component(modules = arrayOf(ThreadModule::class), dependencies = arrayOf(ApplicationComponent::class))
    interface ThreadComponent {
        fun inject(activity: ThreadActivity)
    }

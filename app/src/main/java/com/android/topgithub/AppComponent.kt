package com.android.topgithub

import com.android.topgithub.network.NetworkModule
import com.android.topgithub.view.GithubRepositoryListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, AppModule::class])
@Singleton
interface AppComponent {
        fun inject(githubRepositoryListFragment : GithubRepositoryListFragment)

}
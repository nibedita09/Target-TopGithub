package com.android.topgithub

import android.content.Context
import com.android.topgithub.service.GithubApi
import com.android.topgithub.viewmodel.GithubRepositoryListViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.Reusable
import javax.inject.Singleton

/**
 * Created by Nibedita on 03/12/2018.
 */
@Module
class AppModule(val application: TopGithubApplication) {

    @Provides
    @Singleton
    fun provideApplication() : TopGithubApplication{
        return application
    }

    @Singleton
    @Reusable
    fun provideViewModelFactory(githubApi: GithubApi): GithubRepositoryListViewModelFactory{
        return GithubRepositoryListViewModelFactory(githubApi)
    }
}
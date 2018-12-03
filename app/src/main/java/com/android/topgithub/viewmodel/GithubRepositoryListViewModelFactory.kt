package com.android.topgithub.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.android.topgithub.service.GithubApi
import javax.inject.Inject

class GithubRepositoryListViewModelFactory @Inject constructor(val githubApi : GithubApi): ViewModelProvider.Factory  {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GithubRepositoryListViewModel::class.java)) {
            return GithubRepositoryListViewModel(githubApi) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
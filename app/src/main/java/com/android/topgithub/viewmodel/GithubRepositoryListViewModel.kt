package com.android.topgithub.viewmodel

import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.android.topgithub.model.Repository
import com.android.topgithub.service.GithubApi
import io.reactivex.Observable
import javax.inject.Inject

class GithubRepositoryListViewModel @Inject constructor(val githubApi : GithubApi) : ViewModel() {

  val loadingVisibility = ObservableBoolean(false)

  fun getReposObservable() : Observable<List<Repository>> {
    return githubApi.getGithubRepos("java");
  }

  fun setLoading(value  : Boolean){
    loadingVisibility.set(value)
  }
}
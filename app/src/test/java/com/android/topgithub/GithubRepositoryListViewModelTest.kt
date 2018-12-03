package com.android.topgithub

import com.android.topgithub.model.Repo
import com.android.topgithub.model.Repository
import com.android.topgithub.service.GithubApi
import com.android.topgithub.viewmodel.GithubRepositoryListViewModel
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class GithubRepositoryListViewModelTest {

    @Mock
    private lateinit var mockGithubApi : GithubApi

    private lateinit var viewModel : GithubRepositoryListViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = GithubRepositoryListViewModel(mockGithubApi)
    }

    @Test
    fun test_getReposObservable(){
        var language : String = "dummy"
        val dummyRepo = Repository("testuname", "testname", "testurl", "testavatar", Repo("repotestname", "rtestdesc", "rtesturl"))
        Mockito.`when`(mockGithubApi.getGithubRepos(language)).thenReturn(Observable.just(listOf(dummyRepo)))
        viewModel.getReposObservable().firstElement().test().assertNoErrors().assertValue{
            list:List<Repository> -> list.size == 1 && list[0].avatar.equals("testavatar")
        }
    }
}
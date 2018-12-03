package com.android.topgithub.service

import com.android.topgithub.model.Repository
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface GithubApi {

    @GET(UrlConstants.URL_REPOSITORIES)
    fun getGithubRepos(@Query("language") language : String): Observable<List<Repository>>

}
package com.android.topgithub

import android.app.Application
import com.android.topgithub.network.NetworkModule

/**
 * Created by Nibedita on 03/12/2018.
 */
class TopGithubApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initializeDagger()
    }

    fun initializeDagger() {
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule).build()
    }
}
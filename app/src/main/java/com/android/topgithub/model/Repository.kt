package com.android.topgithub.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@SuppressLint("ParcelCreator")
@Parcelize
data class Repository(
        val username : String,
        val name : String,
        val url : String,
        val avatar : String,
        val repo : Repo
) : Parcelable

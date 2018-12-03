package com.android.topgithub.model

import android.annotation.SuppressLint
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class Repo(
    val name: String,
    val description: String,
    val url: String) : Parcelable



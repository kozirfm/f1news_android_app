package ru.kozirfm.f1news.data.entites

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Team(
    @Expose
    val drivers: List<Driver>,
    @Expose
    val name: String,
    @Expose
    var points: Int
): Parcelable
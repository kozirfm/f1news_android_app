package ru.kozirfm.f1news.data.entites

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Driver(
    @Expose
    val position: Int,
    @Expose
    val name: String,
    @Expose
    val surname: String,
    @Expose
    val team: String,
    @Expose
    val points: Int): Parcelable
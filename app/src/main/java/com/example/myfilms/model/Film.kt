package com.example.myfilms.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class Film(
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val image: String,
    @SerializedName("release_date")
    val date: String,
    @SerializedName("vote_average")
    val rating: Number,
    @SerializedName("vote_count")
    val voteCount: Int
) : Parcelable

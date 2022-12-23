package com.gultendogan.pixabayapp.data.entity

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.gultendogan.pixabayapp.data.entity.Hit

@Keep
data class PixaBayResponse(
    @SerializedName("hits") val hits: List<Hit>,
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int
)
package com.gultendogan.pixabayapp.model.network.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.gultendogan.pixabayapp.model.network.dto.Hit

@Keep
data class PixaBayResponse(
    @SerializedName("hits") val hits: List<Hit>,
    @SerializedName("total") val total: Int,
    @SerializedName("totalHits") val totalHits: Int
)
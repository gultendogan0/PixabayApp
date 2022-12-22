package com.gultendogan.pixabayapp.data

data class ImageResponse(
    val hits: List<PixabayBean.Hit>,
    val total: Int,
    val totalHits: Int
)
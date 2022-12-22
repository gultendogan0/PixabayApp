package com.gultendogan.pixabayapp.data

data class ImageResponse(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
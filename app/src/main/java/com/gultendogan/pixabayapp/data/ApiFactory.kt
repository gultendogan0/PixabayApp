package com.gultendogan.pixabayapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiFactory {

    //https://pixabay.com/api/?key=32237105-70788f503b71c8de73076ab0b&q=yellow+flowers&pretty=true
    //BASE_URL -> "https://pixabay.com/"
    //key -> 32237105-70788f503b71c8de73076ab0b


    @GET("api/?key=32237105-70788f503b71c8de73076ab0b")
    suspend fun getAllImages(): PixabayBean

}

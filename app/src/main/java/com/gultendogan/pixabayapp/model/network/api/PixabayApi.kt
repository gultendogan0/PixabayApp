package com.gultendogan.pixabayapp.model.network.api

import com.gultendogan.pixabayapp.model.network.response.PixaBayResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayApi {

    @GET("api/")
    suspend fun search(
        @Query("q") word: String
    ): Response<PixaBayResponse>

    @GET("api/")
    suspend fun editorsChoice(
        @Query("editors_choice") editorsChoice: Boolean = true
    ): Response<PixaBayResponse>

}
package com.gultendogan.pixabayapp.repository

import com.gultendogan.pixabayapp.data.ApiFactory
import com.gultendogan.pixabayapp.data.Hit
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiFactory: ApiFactory
) {



    //suspend fun getData(
    //    page : Int
    //): CharacterResponse{
    //    return apiFactory.getAllCharacter(page)
    //}

    suspend fun getImages(): List<Hit> {
        return apiFactory.getAllImages()
    }

}
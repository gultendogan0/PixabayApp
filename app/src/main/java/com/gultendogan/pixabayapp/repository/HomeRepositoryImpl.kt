package com.gultendogan.pixabayapp.repository

import com.gultendogan.pixabayapp.data.ApiFactory
import com.gultendogan.pixabayapp.data.PixabayBean
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val apiFactory: ApiFactory
) {



    //suspend fun getData(
    //    page : Int
    //): CharacterResponse{
    //    return apiFactory.getAllCharacter(page)
    //}

    suspend fun getImages(): PixabayBean {
        return apiFactory.getAllImages()
    }

}
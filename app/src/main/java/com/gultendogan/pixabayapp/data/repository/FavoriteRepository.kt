package com.gultendogan.pixabayapp.data.repository

import androidx.lifecycle.LiveData
import com.gultendogan.pixabayapp.data.entity.Hit
import com.gultendogan.pixabayapp.data.room.PixabayDao
import javax.inject.Inject

class FavoriteRepository @Inject constructor(private val pixabayDao: PixabayDao) {
    val getFavorites = pixabayDao.getFavorites()

    suspend fun insertFavorite(hit: Hit) {
        pixabayDao.insertFavorite(hit)
    }

}
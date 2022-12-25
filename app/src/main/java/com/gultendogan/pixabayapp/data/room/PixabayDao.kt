package com.gultendogan.pixabayapp.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gultendogan.pixabayapp.data.entity.Hit

@Dao
interface PixabayDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(hit: Hit)

    @Query("Select * From pixebayItem")
    fun getFavorites(): LiveData<List<Hit>>
}
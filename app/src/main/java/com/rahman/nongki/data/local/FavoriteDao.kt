package com.rahman.nongki.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(favorite: Favorite)

    @Query("SELECT * FROM places")
    fun getFav(): LiveData<List<Favorite>>

    @Delete
    suspend fun deleteFav(favorite: Favorite)
}
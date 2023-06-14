package com.rahman.nongki.data.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rahman.nongki.model.dto.OverviewItem

@Dao
interface FavoriteDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFav(userItem: OverviewItem)

    @Query("SELECT * FROM places")
    fun getFav(): LiveData<List<OverviewItem>>

    @Delete
    suspend fun deleteFav(userItem: OverviewItem)
}
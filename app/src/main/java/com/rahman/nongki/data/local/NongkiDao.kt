package com.rahman.nongki.data.local

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface NongkiDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(Nongki: Nongki)

    @Delete
    fun delete(Nongki: Nongki)

    @Query("SELECT * FROM nongki WHERE name = :name")
    fun getFavoritePlaceByUsername(name: String): LiveData<Nongki>

    @Query("SELECT * FROM nongki")
    fun getAllPlaces(): LiveData<List<Nongki>>
}
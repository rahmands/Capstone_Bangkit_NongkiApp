package com.rahman.nongki.model

import android.app.Application
import androidx.lifecycle.LiveData
import com.rahman.nongki.data.local.Nongki
import com.rahman.nongki.data.local.NongkiDao
import com.rahman.nongki.data.local.NongkiRoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class Repository (application: Application){
    private val mNongkiDao: NongkiDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val database = NongkiRoomDatabase.getDatabase(application)
        mNongkiDao = database.nongkiDao()
    }

    fun getAllPlaces(): LiveData<List<Nongki>> = mNongkiDao.getAllPlaces()
    fun insert(nongki: Nongki) {
        executorService.execute { mNongkiDao.insert(nongki) }
    }

    fun delete(nongki: Nongki) {
        executorService.execute { mNongkiDao.delete(nongki) }
    }

    fun getFavoritePlaceByUsername(namePlace: String): LiveData<Nongki> =
        mNongkiDao.getFavoritePlaceByUsername(namePlace)
}

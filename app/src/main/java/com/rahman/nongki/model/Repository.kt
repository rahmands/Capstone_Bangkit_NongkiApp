package com.rahman.nongki.model

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.LiveData
import com.rahman.nongki.data.local.Favorite
import com.rahman.nongki.data.local.FavoriteDao
import com.rahman.nongki.data.local.FavoriteDatabase
import com.rahman.nongki.data.local.SettingDataStore
import com.rahman.nongki.data.remote.ApiConfig
import com.rahman.nongki.data.remote.ApiService
import com.rahman.nongki.model.dto.DetailResponse
import com.rahman.nongki.model.dto.LoginResponse
import com.rahman.nongki.model.dto.RecommendationResponse
import com.rahman.nongki.model.dto.RegisterResponse

class Repository (val apiService: ApiService, val dataStore: SettingDataStore, val database: FavoriteDao){

    val token = dataStore.getToken()
    suspend fun login(email: String, password: String): LoginResponse {
        val result = apiService.login(email, password)
        dataStore.saveLoginSession(result.data?.get(0)!!.userID!!)
        return result
    }

    suspend fun register(username: String, email: String, password: String): RegisterResponse {
        return apiService.register(username, email, password)
    }

    suspend fun recommendationPlace(key: String): RecommendationResponse {
        Log.e(
            "test",
            key
        )
        val result = apiService.getRecommendationPlaces(key)
        Log.e("test3", result.error.toString())
        return result
    }


    suspend fun detailPlace(placeid: String): DetailResponse {
        return apiService.getDetailPlace(placeid)
    }

    suspend fun addFavorite(item: Favorite): Result<String>{
        try {
            database.addFav(item)
            return Result.success("Berhasil")
        }catch (e: Exception){
            return Result.failure(e)
        }
    }

    //get fav
    val favorite: LiveData<List<Favorite>> = database.getFav()

    suspend fun delFavorite(item: Favorite){
        return database.deleteFav(item)
    }

    suspend fun logout(): Boolean? {
        val result = apiService.logout()
        if (result.error == false){
            dataStore.clearLoginSession()
            return result.error
        } else {
            return result.error
        }
    }

    fun cekFavorite(placeID: String, favorites: List<Favorite>): Boolean? {

        return hasFavorite(placeID,favorites)

    }

    fun hasFavorite(overview: String, favorites: List<Favorite>): Boolean {
        for (favorite in favorites) {
            if (favorite.placeID == overview) {
                return true     // Found a match!
            }
        }
        return false    // No match found
    }


    companion object {
        @Volatile
        private var instance: Repository? = null
        private const val PAGE_SIZE = 10
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "auth")

        fun getInstance(context: Context): Repository {
            return instance ?: synchronized(Repository::class.java) {
                if (instance == null) {
                    val apiConfig = ApiConfig.getApiService()
                    val datastore = SettingDataStore.getInstance(context.dataStore)
                    val database = FavoriteDatabase.getInstance(context)
                    instance = Repository( apiConfig, datastore, database.favoriteDao())
                }
                return instance as Repository
            }
        }
    }
}

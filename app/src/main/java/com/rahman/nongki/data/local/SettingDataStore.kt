package com.rahman.nongki.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.rahman.nongki.model.dto.DataItemLogin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "setting")

class SettingDataStore (
    private val dataStore: DataStore<Preferences>
    ){

    //private val TOKEN = stringPreferencesKey("token")

    private val USER_ID = stringPreferencesKey("user_id")

    fun getToken(): Flow<String> {
        return dataStore.data.map {
            it[USER_ID].toString()
        }
    }



    suspend fun saveLoginSession(token: String){
        dataStore.edit {
            it[USER_ID] = token
        }
    }

    suspend fun clearLoginSession(){
        dataStore.edit {
            it.clear()
        }
    }

    suspend fun saveUserId(userId : List<DataItemLogin?>?){
        dataStore.edit {
            userId.toString()
        }
    }

    fun getUserId(): Flow<String> {
        return dataStore.data.map {
            it[USER_ID].toString()
        }
    }


    companion object {
        @Volatile
        private var INSTANCE: SettingDataStore? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}
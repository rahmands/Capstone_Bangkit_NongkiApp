package com.rahman.nongki

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.rahman.nongki.data.local.SettingDataStore
import com.rahman.nongki.model.dto.DataItemLogin
import kotlinx.coroutines.launch

class SettingViewModel(private val pref : SettingDataStore) : ViewModel(){

    fun getToken(): LiveData<String?> = pref.getToken().asLiveData()
    fun getUserId(): LiveData<String?> = pref.getUserId().asLiveData()


    fun clearLoginSession(){
        viewModelScope.launch {
            pref.clearLoginSession()
        }
    }

    fun saveUserId(userId: List<DataItemLogin?>?){
        viewModelScope.launch {
            pref.saveUserId(userId)
        }
    }
}
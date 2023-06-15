package com.rahman.nongki.view.main

import android.util.Log
import androidx.lifecycle.*
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.DataItem
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class BottomNavViewModel(val repository: Repository) : ViewModel() {
    private val _recommendationList = MutableLiveData<List<DataItem?>?>()
    val recommendationList: LiveData<List<DataItem?>?> = _recommendationList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _nearbyList = MutableLiveData<List<DataItem>>()
    val nearbyList: LiveData<List<DataItem>> = _nearbyList

    private var _token = MutableLiveData<String>()
    val token : LiveData<String> = _token


    val key = repository.token.asLiveData()
    fun getRecommendationData(key: String){
        viewModelScope.launch {
            try {
                val result = repository.recommendationPlace(key)
                Log.e("bismillah", result.data.toString() + result.error)
                if (result.error == false){
                    _recommendationList.value = result.data
                    _message.value = "Berhasil"
                } else {
                    _message.value = "Gagal"
                }

            } catch (e:Exception) {
                _message.value = e.message?: "Error"
            }
        }
    }

    //get favorite
    val favorite = repository.favorite

    fun logout(){
        viewModelScope.launch {
            try {
                val result = repository.logout()
                if (result == false){
                    _message.value = "Berhasil"
                } else {
                    _message.value = "Gagal"
                }

            } catch (e:Exception) {
                _message.value = e.message?: "Error"
            }
        }
    }

    init {
        repository.token.onEach {
            _token.value = it
        }
    }

}
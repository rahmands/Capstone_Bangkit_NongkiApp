package com.rahman.nongki.view.rekomendasi

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.DetailResponse
import com.rahman.nongki.model.dto.OverviewItem
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _detailList = MutableLiveData<DetailResponse?>()
    val detailList: LiveData<DetailResponse?> = _detailList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    fun getDetail(placeid: String){
        viewModelScope.launch {
            try {
                val result = repository.detailPlace(placeid)
                if (result.error == false){
                    _detailList.value = result
                    _message.value = "Berhasil"
                } else {
                    _message.value = "Gagal"
                }

            } catch (e:Exception) {
                _message.value = e.message?: "Error"
            }
        }
    }

    fun addFavorite(item: OverviewItem){
        viewModelScope.launch {
            repository.addFavorite(item)
        }
    }
}
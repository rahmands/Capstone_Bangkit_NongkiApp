package com.rahman.nongki.view.rekomendasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.DetailResponse
import com.rahman.nongki.model.dto.OverviewItem
import com.rahman.nongki.model.dto.ReviewsItem
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _detailList = MutableLiveData<DetailResponse?>()
    val detailList: LiveData<DetailResponse?> = _detailList

    private val _reviewList = MutableLiveData<List<ReviewsItem?>?>()
    val reviewList: LiveData<List<ReviewsItem?>?> = _reviewList

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

    fun getReview(placeid: String){
        viewModelScope.launch {
            try {
                val result = repository.detailPlace(placeid)
                Log.e("review??", result.reviews.toString() + result.error)
                if (result.error == false) {
                    _reviewList.value = result.reviews
                    _message.value = "Berhasil"
                } else {
                    _message.value = "Gagal"
                }
            } catch (e: Exception){
                _message.value = e.message?: "Error"
            }
        }
    }

    //get favorite
    val favorite = repository.favorite

    fun addFavorite(item: OverviewItem){
        Log.e("ADD", "harusnya masuk nih")
        viewModelScope.launch {
            repository.addFavorite(item)
        }
    }

    fun delFavorite(item: OverviewItem){
        viewModelScope.launch {
            repository.delFavorite(item)
        }
    }
}
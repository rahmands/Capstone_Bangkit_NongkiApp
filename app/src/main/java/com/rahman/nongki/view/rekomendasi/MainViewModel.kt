package com.rahman.nongki.view.rekomendasi

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rahman.nongki.data.local.Favorite
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.DetailResponse
import com.rahman.nongki.model.dto.ReviewsItem
import kotlinx.coroutines.launch

class MainViewModel(val repository: Repository) : ViewModel() {

    private val _detailList = MutableLiveData<DetailResponse?>()
    val detailList: LiveData<DetailResponse?> = _detailList

    private val _reviewList = MutableLiveData<List<ReviewsItem?>?>()
    val reviewList: LiveData<List<ReviewsItem?>?> = _reviewList

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> = _message

    private val _favorite = MutableLiveData<Boolean>()
    val favorite: LiveData<Boolean> = _favorite

    val listFavorite = repository.favorite


    fun getDetail(placeid: String, favorites: List<Favorite>){
        viewModelScope.launch {
            try {
                val result = repository.detailPlace(placeid)
                if (result.error == false){
                    cekFavorite(placeid, favorites)
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



    fun addFavorite(item: Favorite){
        Log.e("ADD", "harusnya masuk nih")
        viewModelScope.launch {
            try {
                _message.value = repository.addFavorite(item).getOrThrow()
                _favorite.value = true
            }catch (e:Exception){
                _message.value = e.message
            }
        }
    }



    fun delFavorite(item: Favorite){
        viewModelScope.launch {
            repository.delFavorite(item)
        }
    }

    fun cekFavorite(placeID: String, favorites: List<Favorite>) {
        viewModelScope.launch {
           _favorite.value = repository.cekFavorite(placeID, favorites)
        }
    }
}
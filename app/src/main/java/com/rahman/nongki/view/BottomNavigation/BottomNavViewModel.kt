package com.rahman.nongki.view.BottomNavigation

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rahman.nongki.data.local.Nongki
import com.rahman.nongki.data.remote.ApiAppConfig
import com.rahman.nongki.model.Repository
import com.rahman.nongki.model.dto.DataItem
import com.rahman.nongki.model.dto.RecommendationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BottomNavViewModel(mApplication: Application) : ViewModel() {
    private val _recommendationList = MutableLiveData<List<DataItem>>()
    val recommendationList: LiveData<List<DataItem>> = _recommendationList

    //private val _nearbyList = MutableLiveData<List<DataItem>>()
    //val nearbyList: LiveData<List<DataItem>> = _nearbyList

    private val mRepository: Repository = Repository(mApplication)

    fun getAllPlaces(): LiveData<List<Nongki>> = mRepository.getAllPlaces()

    fun getRecommendationData(UserId: String){
        val recommendation = ApiAppConfig.getApiService().getRecommendationPlaces(UserId)
        recommendation.enqueue(object : Callback<RecommendationResponse> {
            override fun onResponse(
                call: Call<RecommendationResponse>,
                response: Response<RecommendationResponse>
            ) {
                if (response.isSuccessful) {
                    val recommendationList = response.body()?.data
                    recommendationList.let {
                        _recommendationList.value = it as List<DataItem>?
                    }
                }
            }

            override fun onFailure(call: Call<RecommendationResponse>, t: Throwable) {
            }

        })
    }

    /*fun getNearbyData(){
        val nearby = ApiAppConfig.getApiService().getNearbyPlaces(userId = "" , placeType = "")
        nearby.enqueue(object : Callback<NearbyResponse> {
            override fun onResponse(
                call: Call<NearbyResponse>,
                response: Response<NearbyResponse>
            ) {
                if (response.isSuccessful) {
                    val nearbyList = response.body()?.data
                    nearbyList.let {
                        _nearbyList.value = it as List<DataItem>?
                    }
                }
            }

            override fun onFailure(call: Call<NearbyResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }*/

}
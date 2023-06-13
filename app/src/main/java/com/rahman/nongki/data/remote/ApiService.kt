package com.rahman.nongki.data.remote

import com.rahman.nongki.model.dto.LoginResponse

import com.rahman.nongki.model.dto.RecommendationResponse
import com.rahman.nongki.model.dto.RegisterResponse
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("/api/recommendation-place/{key}")
    fun getRecommendationPlaces(
        @Query("key") userId: String
        //@Query("type") placeType: String?
    ): Call<RecommendationResponse>

    //@GET("/api/nearby-place/")
    //fun getNearbyPlaces(
      //  @Query("key") userId: String,
        //@Query("type") placeType: String?
    //): Call<NearbyResponse>

    //@GET("/api/detail-place/{place_id}")
    //fun getPlaceDetail(
        //@Path("place_id") placeId: String): Call<PlaceDetailResponse>

    @POST("/register")
    @FormUrlEncoded
    fun register(
        @Field("name") name : String,
        @Field("email") email : String,
        @Field("password") password : String
    ):Call<RegisterResponse>

    @POST("/login")
    @FormUrlEncoded
    fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ): Call<LoginResponse>
}
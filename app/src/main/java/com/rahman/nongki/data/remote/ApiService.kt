package com.rahman.nongki.data.remote

import com.rahman.nongki.model.dto.*

import retrofit2.http.*

interface ApiService {

    @POST("/register")
    @FormUrlEncoded
    suspend fun register(
        @Field("fullname") fullname : String,
        @Field("email") email : String,
        @Field("password") password : String
    ):RegisterResponse

    @POST("/login")
    @FormUrlEncoded
    suspend fun login(
        @Field("email") email : String,
        @Field("password") password : String
    ): LoginResponse

    @GET("/logout")
    suspend fun logout(
    ): LogoutResponse

    @GET("/logged")
    suspend fun logged(
    ): LoggedResponse

    @GET("/api/recomendation-place/")
    suspend fun getRecommendationPlaces(
        @Query("key") key: String
    ): RecommendationResponse


    @GET("/api/detail-place/{place_id}")
    suspend fun getDetailPlace(
        @Path("place_id") place_id: String
    ): DetailResponse
}
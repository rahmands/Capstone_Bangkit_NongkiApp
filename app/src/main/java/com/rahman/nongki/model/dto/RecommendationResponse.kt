package com.rahman.nongki.model.dto

import com.google.gson.annotations.SerializedName

data class RecommendationResponse(

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("error")
	val error: String? = null
)

data class DataItem(
	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("StreetAddress")
	val streetAddress: String?,

	@field:SerializedName("photoReference")
	val photoReference: String,

	@field:SerializedName("distance")
	val distance: Double,

	@field:SerializedName("Regency")
	val regency: String?,

	@field:SerializedName("OverallRating")
	val overallRating: Double,

	@field:SerializedName("Latitude")
	val latitude: Double,

	@field:SerializedName("City")
	val city: String?,

	@field:SerializedName("Longitude")
	val longitude: Double,

	@field:SerializedName("Province")
	val province: String?,

	@field:SerializedName("distanceTime")
	val distanceTime: Double,

	@field:SerializedName("UserRatingTotal")
	val userRatingTotal: Int,

	@field:SerializedName("District")
	val district: String?,

	@field:SerializedName("place_id")
	val placeId: String,

	@field:SerializedName("user_id")
	val userId: String
)

package com.rahman.nongki.model.dto

import com.google.gson.annotations.SerializedName

data class  RecommendationResponse(
	@field:SerializedName("error")
	val error: Boolean? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null
)

data class DataItem(
	@field:SerializedName("place_id")
	val placeId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("Latitude")
	val latitude: Double,

	@field:SerializedName("Longitude")
	val longitude: Double,

	@field:SerializedName("OverallRating")
	val overallRating: Double,

	@field:SerializedName("UserRatingTotal")
	val userRatingTotal: Int,

	@field:SerializedName("StreetAddress")
	val streetAddress: String?,

	@field:SerializedName("District")
	val district: String?,

	@field:SerializedName("City")
	val city: String?,

	@field:SerializedName("Regency")
	val regency: String?,

	@field:SerializedName("Province")
	val province: String?,

	@field:SerializedName("distance")
	val distance: Double,

	@field:SerializedName("distanceTime")
	val distanceTime: Double,

	@field:SerializedName("photoReference")
	val photoReference: String,
)

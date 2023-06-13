package com.rahman.nongki.model.dto

import com.google.gson.annotations.SerializedName

data class DetailResponse(

	@field:SerializedName("overview")
	val overview: List<OverviewItem?>? = null,

	@field:SerializedName("reviews")
	val reviews: List<ReviewsItem?>? = null,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("tags")
	val tags: List<TagsItem?>? = null
)

data class OverviewItem(

	@field:SerializedName("Regency")
	val regency: String? = null,

	@field:SerializedName("OverallRating")
	val overallRating: String? = null,

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("FormattedAddress")
	val formattedAddress: String? = null,

	@field:SerializedName("Latitude")
	val latitude: String? = null,

	@field:SerializedName("PostalNumber")
	val postalNumber: String? = null,

	@field:SerializedName("City")
	val city: String? = null,

	@field:SerializedName("Longitude")
	val longitude: String? = null,

	@field:SerializedName("Province")
	val province: String? = null,

	@field:SerializedName("Name")
	val name: String? = null,

	@field:SerializedName("StreetAddress")
	val streetAddress: String? = null,

	@field:SerializedName("FormattedPhone")
	val formattedPhone: String? = null,

	@field:SerializedName("Place_ID")
	val placeID: String? = null,

	@field:SerializedName("UserRatingTotal")
	val userRatingTotal: String? = null,

	@field:SerializedName("District")
	val district: String? = null,

	@field:SerializedName("close")
	val close: String? = null,

	@field:SerializedName("open")
	val open: String? = null
)

data class TagsItem(

	@field:SerializedName("categories")
	val categories: List<String?>? = null,

	@field:SerializedName("services")
	val services: List<String?>? = null
)

data class ReviewsItem(

	@field:SerializedName("star")
	val star: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("reviewtext")
	val reviewtext: String? = null
)

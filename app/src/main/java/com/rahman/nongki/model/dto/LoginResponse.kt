package com.rahman.nongki.model.dto

import com.google.gson.annotations.SerializedName

data class LoginResponse(

	@field:SerializedName("data")
	val data: List<DataItemLogin?>? = null,
	//val data: DataItemLogin,

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null
):java.io.Serializable

data class DataItemLogin(

	@field:SerializedName("Email")
	val email: String? = null,

	@field:SerializedName("FullName")
	val fullName: String? = null,

	@field:SerializedName("Latitude")
	val latitude: String? = null,

	@field:SerializedName("User_ID")
	val userID: String? = null,

	@field:SerializedName("Longitude")
	val longitude: String? = null,

	@field:SerializedName("Password")
	val password: String? = null,

	@field:SerializedName("token")
	val token : String
)

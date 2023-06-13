package com.rahman.nongki.model.dto

import com.google.gson.annotations.SerializedName

data class LoggedResponse(

	@field:SerializedName("error")
	val error: String? = null,

	@field:SerializedName("message")
	val message: String? = null
)

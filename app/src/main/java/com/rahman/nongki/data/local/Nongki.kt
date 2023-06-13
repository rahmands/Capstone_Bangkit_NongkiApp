package com.rahman.nongki.data.local

import android.annotation.SuppressLint
import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "nongki")
data class Nongki(
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

    @PrimaryKey
    @field:SerializedName("place_id")
    val placeId: String
): Parcelable


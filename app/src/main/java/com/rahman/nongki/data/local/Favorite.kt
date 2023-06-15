package com.rahman.nongki.data.local

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "places")
data class Favorite(
    @PrimaryKey(autoGenerate = false)
    @field:SerializedName("Place_ID")
    val placeID: String,

    @field:SerializedName("images")
    val images: String? = null,

    @field:SerializedName("Name")
    val name: String? = null,

    @field:SerializedName("StreetAddress")
    val streetAddress: String? = null,

    @field:SerializedName("OverallRating")
    val overallRating: String? = null,
):Parcelable

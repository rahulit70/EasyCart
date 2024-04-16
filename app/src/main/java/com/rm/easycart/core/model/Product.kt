package com.rm.easycart.core.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.AppConstants.Companion.EMPTY_STRING

@Entity(tableName = AppConstants.TABLE_PRODUCT)
data class Product(
    @SerializedName("id")
    @PrimaryKey
    var id: Int = 0,

    @SerializedName("brand")
    var brand: String = EMPTY_STRING,

    @SerializedName("category")
    var category: String = EMPTY_STRING,

    @SerializedName("description")
    var description: String = EMPTY_STRING,

    @SerializedName("discountPercentage")
    var discountPercentage: Double = 0.0,


    @SerializedName("price")
    var price: Int = 0,

    @SerializedName("rating")
    var rating: Double = 0.0,

    @SerializedName("stock")
    var stock: Int = 0,

    @SerializedName("thumbnail")
    var thumbnail: String = EMPTY_STRING,

    @SerializedName("title")
    var title: String = EMPTY_STRING,

    @Ignore
    @SerializedName("images")
    var images: List<String>? = null
)
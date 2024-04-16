package com.rm.easycart.core.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("products")
    var products: List<Product> = listOf(),
    @SerializedName("limit")
    var limit: Int = 0,
    @SerializedName("skip")
    var skip: Int = 0,
    @SerializedName("total")
    var total: Int = 0,
)
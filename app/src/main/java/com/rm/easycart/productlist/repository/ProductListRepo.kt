package com.rm.easycart.productlist.repository

import com.rm.easycart.core.model.ProductsResponse
import retrofit2.Response

fun interface ProductListRepo {
    suspend operator fun invoke(page: Int): Response<ProductsResponse>
}
package com.rm.easycart.productlist.usecase

import com.rm.easycart.core.model.ProductsResponse
import retrofit2.Response

fun interface GetProductListUseCase {
    suspend operator fun invoke(page: Int): Response<ProductsResponse>
}
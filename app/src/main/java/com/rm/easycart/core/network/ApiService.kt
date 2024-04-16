package com.rm.easycart.core.network

import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET(ApiRoutes.GET_PRODUCTS)
    suspend fun getProducts(
        @Query(AppConstants.LIMIT) limit: Int,
        @Query(AppConstants.SKIP) skip: Int
    ): Response<ProductsResponse>

    @GET(ApiRoutes.GET_PRODUCT_BY_ID)
    suspend fun getProductById(
        @Path(AppConstants.PID) productId: Int
    ): Response<Product>
}
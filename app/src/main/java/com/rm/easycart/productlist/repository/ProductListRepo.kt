package com.rm.easycart.productlist.repository

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

fun interface ProductListRepo {
    suspend operator fun invoke(page:Int): Response<ProductsResponse>
}
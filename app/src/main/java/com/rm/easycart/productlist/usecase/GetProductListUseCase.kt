package com.rm.easycart.productlist.usecase

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

fun interface GetProductListUseCase {
    suspend operator fun invoke(page:Int): Response<ProductsResponse>
}
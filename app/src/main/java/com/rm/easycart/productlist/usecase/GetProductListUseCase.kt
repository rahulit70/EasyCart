package com.rm.easycart.productlist.usecase

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import kotlinx.coroutines.flow.Flow

fun interface GetProductListUseCase {
    suspend operator fun invoke(): Flow<PagingData<Product>>
}
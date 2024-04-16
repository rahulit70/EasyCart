package com.rm.easycart.productlist.repository

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import kotlinx.coroutines.flow.Flow

fun interface ProductListRepo {
    operator fun invoke(): Flow<PagingData<Product>>
}
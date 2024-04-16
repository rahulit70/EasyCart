package com.rm.easycart.productdetails.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow

fun interface CartRepository {
    operator fun invoke(product: Product): Flow<Resource<Long>>
}
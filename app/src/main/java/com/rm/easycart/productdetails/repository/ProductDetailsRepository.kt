package com.rm.easycart.productdetails.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow

fun interface ProductDetailsRepository {
    operator fun invoke(productId: Int): Flow<Resource<Product>>
}
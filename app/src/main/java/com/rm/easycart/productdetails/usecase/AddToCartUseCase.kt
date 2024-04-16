package com.rm.easycart.productdetails.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow

fun interface AddToCartUseCase {
    operator fun invoke(product: Product): Flow<Resource<Long>>
}
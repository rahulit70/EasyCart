package com.rm.easycart.cart.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow

fun interface LoadCartUseCase {
    suspend operator fun invoke(): Flow<Resource<List<Product>>>
}
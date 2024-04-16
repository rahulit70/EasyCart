package com.rm.easycart.cart.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import kotlinx.coroutines.flow.Flow

interface CartRepository {
    suspend fun getAllCartItems(): Flow<Resource<List<Product>>>
}
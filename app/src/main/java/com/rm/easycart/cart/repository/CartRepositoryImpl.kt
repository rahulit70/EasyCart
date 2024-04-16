package com.rm.easycart.cart.repository

import com.rm.easycart.core.db.ProductDAO
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class CartRepositoryImpl @Inject constructor(
    private val dao: ProductDAO
) : CartRepository {
    override suspend fun getAllCartItems(): Flow<Resource<List<Product>>> = flow {
        emit(Resource.Loading())
        val items = dao.getProducts()
        if (items.isNotEmpty()) emit(Resource.Success(items, message = AppConstants.API_SUCCESS))
        else emit(Resource.Error(message = AppConstants.EMPTY_CART))
    }


}
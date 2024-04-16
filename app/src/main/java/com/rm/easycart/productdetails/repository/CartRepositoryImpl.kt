package com.rm.easycart.productdetails.repository

import com.rm.easycart.core.db.ProductDAO
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class CartRepositoryImpl @Inject constructor(private val dao: ProductDAO) : CartRepository {
    override fun invoke(product: Product): Flow<Resource<Long>> = flow {
        emit(Resource.Loading())
        delay(200)
        val id = dao.insertProduct(product)
        if (id > 0) emit(Resource.Success(data = id, message = AppConstants.DB_SUCCESS))
        else emit(Resource.Error(message = AppConstants.DB_ERROR))
    }
}
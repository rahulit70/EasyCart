package com.rm.easycart.productdetails.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.core.network.ApiService
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@ActivityRetainedScoped
class ProductDetailsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : ProductDetailsRepository {

    override fun invoke(productId: Int): Flow<Resource<Product>> = flow {
        emit(Resource.Loading())
        try {
            val response = apiService.getProductById(productId)
            if (response.code() == AppConstants.SUCCESS_CODE) response.body()?.let {
                if (response.isSuccessful) {
                    emit(Resource.Success(data = it, message = AppConstants.API_SUCCESS))
                } else emit(Resource.Error(message = AppConstants.API_FAILED))
            } else emit(Resource.Error(message = response.message()))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage))
        }
    }

}
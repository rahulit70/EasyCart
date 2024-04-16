package com.rm.easycart.productdetails.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.repository.ProductDetailsRepository
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class GetProductDetailsByIdUseCaseImpl @Inject constructor(
    private val repository: ProductDetailsRepository
) : GetProductDetailsByIdUseCase {
    override fun invoke(productId: Int): Flow<Resource<Product>> = repository.invoke(productId)
}
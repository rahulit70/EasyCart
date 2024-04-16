package com.rm.easycart.productdetails.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.repository.CartRepository
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class AddToCartUseCaseImpl @Inject constructor(
    private val cartRepository: CartRepository
) : AddToCartUseCase {
    override fun invoke(product: Product): Flow<Resource<Long>> = cartRepository.invoke(product)
}
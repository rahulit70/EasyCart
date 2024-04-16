package com.rm.easycart.cart.usecase

import com.rm.easycart.cart.repository.CartRepository
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class LoadCartUseCaseImpl @Inject constructor(
    private val repository: CartRepository
) : LoadCartUseCase {
    override suspend fun invoke(): Flow<Resource<List<Product>>> = repository.getAllCartItems()
}
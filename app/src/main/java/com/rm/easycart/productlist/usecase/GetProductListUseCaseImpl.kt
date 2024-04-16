package com.rm.easycart.productlist.usecase

import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.productlist.repository.ProductListRepo
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class GetProductListUseCaseImpl @Inject constructor(
    private val productListRepo: ProductListRepo
) : GetProductListUseCase {
    override suspend fun invoke(page: Int): Response<ProductsResponse> =
        productListRepo.invoke(page)
}
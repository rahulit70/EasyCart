package com.rm.easycart.productlist.usecase

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.productlist.repository.ProductListRepo
import com.rm.easycart.utils.Resource

import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

@ActivityRetainedScoped
class GetProductListUseCaseImpl @Inject constructor(
    private val productListRepo: ProductListRepo
) : GetProductListUseCase {
    override suspend fun invoke(page:Int): Response<ProductsResponse> = productListRepo.invoke(page)
}
package com.rm.easycart.productlist.repository

import com.rm.easycart.core.network.ApiService
import dagger.hilt.android.scopes.ActivityRetainedScoped
import javax.inject.Inject

@ActivityRetainedScoped
class ProductListRepoImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductListRepo {
    override suspend fun invoke(page: Int) = apiService.getProducts(10, page)
}


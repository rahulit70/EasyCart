package com.rm.easycart.productlist.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.network.ApiService
import com.rm.easycart.utils.AppConstants.Companion.MAX_ITEM_PER_PAGE
import com.rm.easycart.utils.AppConstants.Companion.START_PAGE_INDEX
import dagger.hilt.android.scopes.ActivityRetainedScoped
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ActivityRetainedScoped
class ProductListRepoImpl @Inject constructor(
    private val apiService: ApiService,
) : ProductListRepo {
    override fun invoke(): Flow<PagingData<Product>> = Pager(
        config = PagingConfig(pageSize = MAX_ITEM_PER_PAGE, prefetchDistance = 2),
        pagingSourceFactory = { ProductPagingSource(apiService) }
    ).flow
}

class ProductPagingSource(private val apiService: ApiService) : PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: START_PAGE_INDEX
            apiService.getProducts(10, currentPage).body().let { resp ->
                LoadResult.Page(
                    data = resp?.products ?: listOf(),
                    prevKey = if (currentPage == START_PAGE_INDEX) null else currentPage - MAX_ITEM_PER_PAGE,
                    nextKey = if (resp?.products!!.isEmpty()) null else resp.skip + MAX_ITEM_PER_PAGE
                )
            }
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition
    }

}
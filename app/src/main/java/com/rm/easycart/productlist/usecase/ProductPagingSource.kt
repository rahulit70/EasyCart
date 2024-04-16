package com.rm.easycart.productlist.usecase

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.AppConstants.Companion.MAX_ITEM_PER_PAGE
import com.rm.easycart.utils.AppConstants.Companion.START_PAGE_INDEX

class ProductPagingSource(private val getProductListUseCase: GetProductListUseCase) :
    PagingSource<Int, Product>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Product> {
        return try {
            val currentPage = params.key ?: START_PAGE_INDEX
            getProductListUseCase.invoke(currentPage).body().let { resp ->
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
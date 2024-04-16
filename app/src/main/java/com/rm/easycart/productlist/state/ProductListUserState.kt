package com.rm.easycart.productlist.state

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.ViewState
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Product List User State
 * @param productPagingItems product list pagination data
 * @param dataState is product list data state to hold product list
 */
data class ProductListUserState(
    val productPagingItems: MutableStateFlow<PagingData<Product>> = MutableStateFlow(value = PagingData.empty()),
    val dataState: ViewState<ProductListDataState> = ViewState(ProductListDataState())
)






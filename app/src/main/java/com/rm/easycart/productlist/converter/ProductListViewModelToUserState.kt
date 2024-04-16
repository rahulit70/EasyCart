package com.rm.easycart.productlist.converter

import com.rm.easycart.productlist.state.ProductListUserState
import com.rm.easycart.productlist.viewmodel.ProductListViewModel
import com.rm.easycart.utils.Converter

/**
 * ProductListViewModelToUserState converter
 * Converts ProductListViewModel to product list page User sate [ProductListUserState]
 */
class ProductListViewModelToUserState : Converter<ProductListViewModel, ProductListUserState> {
    override fun convert(input: ProductListViewModel) = with(input) {
        ProductListUserState(productPagingItems = productState)
    }
}
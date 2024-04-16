package com.rm.easycart.productlist.converter

import com.rm.easycart.productlist.viewmodel.ProductListViewModel
import com.rm.easycart.productlist.event.ProductListUserEvent
import com.rm.easycart.utils.Converter

/**
 * ProductListViewModelToUserEvent converter
 * Converts ProductListViewModel to product list page User event [ProductListUserEvent]
 */
class ProductListViewModelToUserEvent : Converter<ProductListViewModel, ProductListUserEvent> {
    override fun convert(input: ProductListViewModel) = with(input) {
        ProductListUserEvent(getProductList = { getProductsList() })
    }
}
package com.rm.easycart.productdetails.converter

import com.rm.easycart.productdetails.event.ProductDetailsUserEvent
import com.rm.easycart.productdetails.viewmodel.ProductDetailsViewModel
import com.rm.easycart.utils.Converter

/**
 * Product Details ViewModel Converter
 * Converts Product Details view model[ProductDetailsViewModel] to  product details user event [ProductDetailsUserEvent]
 */
class ProductDetailsViewModelToUserEvent :
    Converter<ProductDetailsViewModel, ProductDetailsUserEvent> {
    override fun convert(input: ProductDetailsViewModel) = with(input) {
        ProductDetailsUserEvent(
            getProductDetailsById = { getProductDetailsByID(it) },
            addToCart = { addToCart(it) }
        )
    }
}
package com.rm.easycart.productdetails.converter

import com.rm.easycart.productdetails.state.ProductDetailsUserState
import com.rm.easycart.productdetails.viewmodel.ProductDetailsViewModel
import com.rm.easycart.utils.Converter

/**
 * Product Details ViewModel Converter
 * Converts Product Details view model[ProductDetailsViewModel] to  product Details user state [ProductDetailsUserState]
 */
class ProductDetailsViewModelToUserState : Converter<ProductDetailsViewModel, ProductDetailsUserState> {
    override fun convert(input: ProductDetailsViewModel) = with(input) {
        ProductDetailsUserState(
            productDetailsDataState = productDetailsDataState.value,
            addToCartDataState = addToCartDataState.value
        )
    }
}
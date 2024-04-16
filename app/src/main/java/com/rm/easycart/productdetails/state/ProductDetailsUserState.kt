package com.rm.easycart.productdetails.state

import com.rm.easycart.utils.ViewState

/**
 * Product Details User State
 * @param productDetailsDataState is a view state holder to show product details
 * @param addToCartDataState is a view state holder to add a product in to the cart
 */
data class ProductDetailsUserState(
    val productDetailsDataState: ViewState<ProductDetailsDataState> = ViewState(ProductDetailsDataState()),
    val addToCartDataState: ViewState<AddToCartDataState> = ViewState(AddToCartDataState()),
)
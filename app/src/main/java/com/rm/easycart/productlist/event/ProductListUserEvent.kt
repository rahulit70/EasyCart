package com.rm.easycart.productlist.event

/**
 * Home page User event
 * @param getProductList trigger get product list download the list of product form the server
 */
data class ProductListUserEvent(
    val getProductList: () -> Unit = { }
)
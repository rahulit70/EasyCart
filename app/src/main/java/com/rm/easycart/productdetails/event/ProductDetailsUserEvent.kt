package com.rm.easycart.productdetails.event

import com.rm.easycart.core.model.Product

/**
 * Product Details User Event
 * @param getProductDetailsById is a lambda to trigger get product details by ID
 * @param addToCart is a lambda to add product[Product] to the database
 */
data class ProductDetailsUserEvent(
    val getProductDetailsById: (productId: Int?) -> Unit = { _ -> },
    val addToCart: (product: Product) -> Unit = { _ -> }
)
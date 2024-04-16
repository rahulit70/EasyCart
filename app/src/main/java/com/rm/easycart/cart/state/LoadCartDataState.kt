package com.rm.easycart.cart.state

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.ApiState

data class LoadCartDataState(
    var products: List<Product> = listOf(),
    var apiState: ApiState = ApiState.Initial,
)

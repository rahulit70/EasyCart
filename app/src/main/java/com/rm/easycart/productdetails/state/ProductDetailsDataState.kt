package com.rm.easycart.productdetails.state

import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.BaseState

data class ProductDetailsDataState(
    var product: Product = Product(),
    var apiState: ApiState = ApiState.Initial,
) : BaseState()
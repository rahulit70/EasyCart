package com.rm.easycart.productlist.state

import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.BaseState

data class ProductListDataState(
    var getProductsResponse: ProductsResponse = ProductsResponse(),
    var apiState: ApiState = ApiState.Initial,
) : BaseState()
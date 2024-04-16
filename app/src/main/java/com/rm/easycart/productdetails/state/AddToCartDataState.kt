package com.rm.easycart.productdetails.state

import com.rm.easycart.utils.ApiState

data class AddToCartDataState(
    var id: Long = 0,
    var apiState: ApiState = ApiState.Initial
)
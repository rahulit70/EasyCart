package com.rm.easycart.cart.state

import com.rm.easycart.utils.ViewState

/**
 * Cart List User State
 * @param loadCartDataState is view state holder [ViewState<LoadCartDataState>]
 */
data class CartListUserState(
    val loadCartDataState: ViewState<LoadCartDataState> = ViewState(LoadCartDataState()),
)
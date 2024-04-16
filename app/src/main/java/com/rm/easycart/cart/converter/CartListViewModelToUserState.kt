package com.rm.easycart.cart.converter

import com.rm.easycart.cart.state.CartListUserState
import com.rm.easycart.cart.viewmodel.CartListViewModel
import com.rm.easycart.utils.Converter

/**
 * CartListViewModel User State Converter
 * Converts CartListViewModel to CartListUserState
 */
class CartListViewModelToUserState : Converter<CartListViewModel, CartListUserState> {
    override fun convert(input: CartListViewModel) = with(input) {
        CartListUserState(
            loadCartDataState = loadCartDataState.value,
        )
    }
}
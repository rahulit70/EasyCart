package com.rm.easycart.cart.converter

import com.rm.easycart.cart.event.CartListUserEvent
import com.rm.easycart.cart.viewmodel.CartListViewModel
import com.rm.easycart.utils.Converter

/**
 * CartListViewModel User Event Converter
 * Converts CartListViewModel to CartListUserEvent
 */
class CartListViewModelToUserEvent : Converter<CartListViewModel, CartListUserEvent> {
    override fun convert(input: CartListViewModel) = with(input) {
        CartListUserEvent(
            loadCart = { loadCart() }
        )
    }
}
package com.rm.easycart.cart.event

/**
 * Cart List User Events
 * @param loadCart is a lambda function trigger load cart
 */
data class CartListUserEvent(
    val loadCart: () -> Unit = { },
)
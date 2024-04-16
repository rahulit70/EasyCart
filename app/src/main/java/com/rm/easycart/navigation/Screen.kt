package com.rm.easycart.navigation

sealed class Screen(val route: String) {
    data object ProductListScreen : Screen("product_list")
    data object ProductDetailsScreen : Screen("product_details")
    data object CartScreen : Screen("cart")
}
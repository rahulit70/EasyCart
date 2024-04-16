package com.rm.easycart.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rm.easycart.cart.converter.CartListViewModelToUserEvent
import com.rm.easycart.cart.converter.CartListViewModelToUserState
import com.rm.easycart.cart.ui.presentation.CartListUI
import com.rm.easycart.cart.viewmodel.CartListViewModel
import com.rm.easycart.productdetails.converter.ProductDetailsViewModelToUserEvent
import com.rm.easycart.productdetails.converter.ProductDetailsViewModelToUserState
import com.rm.easycart.productdetails.ui.presentation.ProductDetailsUI
import com.rm.easycart.productdetails.viewmodel.ProductDetailsViewModel
import com.rm.easycart.productlist.converter.ProductListViewModelToUserEvent
import com.rm.easycart.productlist.converter.ProductListViewModelToUserState
import com.rm.easycart.productlist.ui.presentation.ProductListUI
import com.rm.easycart.productlist.viewmodel.ProductListViewModel
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.ShowSnackBar

@Composable
fun ScreenNavigation(
    navHostController: NavHostController,
    showSnackBar: ShowSnackBar
) {
    val productDetailsArguments = listOf(
        navArgument(AppConstants.PID) {
            type = NavType.IntType
            defaultValue = 0
        },
    )
    NavHost(
        navController = navHostController,
        startDestination = Screen.ProductListScreen.route
    ) {
        composable(route = Screen.ProductListScreen.route) {
            NavigateToProductListScreen(navHostController, showSnackBar)
        }


        composable(
            route = Screen.ProductDetailsScreen.route.plus("/{${AppConstants.PID}}"),
            arguments = productDetailsArguments
        ) { entry ->
            NavigateToProductDetailsScreen(navHostController, showSnackBar, entry)
        }

        composable(route = Screen.CartScreen.route) {
            NavigateToCartListScreen(navHostController)
        }
    }
}

@Composable
fun NavigateToCartListScreen(
    navController: NavController
) {
    val viewModel = hiltViewModel<CartListViewModel>()
    val userState = CartListViewModelToUserState().convert(viewModel)
    val userEvent = CartListViewModelToUserEvent().convert(viewModel)
    CartListUI(navController, userState, userEvent)

}


@Composable
fun NavigateToProductListScreen(
    navController: NavController,
    showSnackBar: ShowSnackBar
) {
    val viewModel = hiltViewModel<ProductListViewModel>()
    val userState = ProductListViewModelToUserState().convert(viewModel)
    val userEvent = ProductListViewModelToUserEvent().convert(viewModel)
    ProductListUI(
        navController = navController,
        showSnackBar = showSnackBar,
        userState = userState,
        userEvent = userEvent
    )
}

@Composable
fun NavigateToProductDetailsScreen(
    navController: NavController,
    showSnackBar: ShowSnackBar,
    backStackEntry: NavBackStackEntry
) {
    val productId = backStackEntry.arguments?.getInt(AppConstants.PID)
    val viewModel = hiltViewModel<ProductDetailsViewModel>()
    val userState = ProductDetailsViewModelToUserState().convert(viewModel)
    val userEvent = ProductDetailsViewModelToUserEvent().convert(viewModel)
    ProductDetailsUI(
        navController = navController,
        showSnackBar = showSnackBar,
        userState = userState,
        userEvent = userEvent,
        productId = productId
    )
}

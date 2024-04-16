package com.rm.easycart.cart.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rm.easycart.R
import com.rm.easycart.cart.event.CartListUserEvent
import com.rm.easycart.cart.state.CartListUserState
import com.rm.easycart.cart.state.LoadCartDataState
import com.rm.easycart.cart.ui.state.CartListViewState
import com.rm.easycart.core.model.Product
import com.rm.easycart.ui.theme.EasyCartTheme
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.CustomSnackBar
import com.rm.easycart.utils.CustomTopAppBar
import com.rm.easycart.utils.ViewState

@Composable
fun CartListUI(
    navController: NavController = rememberNavController(),
    userState: CartListUserState = CartListUserState(),
    userEvent: CartListUserEvent = CartListUserEvent()
) {
    LaunchedEffect(Unit) { userEvent.loadCart.invoke() }
    CartList(
        navController = navController,
        userState = userState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CartList(
    navController: NavController,
    userState: CartListUserState,
) {
    val snackBarState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { CartListTopAppBar(navController, scrollBehavior) },
        snackbarHost = { CustomSnackBar(snackBarHostState = snackBarState) },
    ) {
        Box(modifier = Modifier.padding(it)) {
            CartListViewState(
                state = userState,
                onCartEmpty = { CartEmptyViewUI() },
                drawContent = { CartListContent(userState) }
            )
        }
    }
}

@Composable
fun CartListContent(
    userState: CartListUserState,
) {
    LazyColumn(modifier = Modifier.padding(vertical = 5.dp)) {
        items(userState.loadCartDataState.value.products) { product ->
            CartItemUI(product)
        }
    }
}

@Composable
private fun CartEmptyViewUI() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(160.dp),
            painter = painterResource(id = R.drawable.baseline_remove_shopping_cart_24),
            contentDescription = stringResource(id = R.string.img_empty_cart)
        )
        Text(
            text = stringResource(id = R.string.no_product_in_cart),
            color = Color.Magenta,
            textAlign = TextAlign.Center
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartListTopAppBar(
    navController: NavController,
    scrollBehavior: TopAppBarScrollBehavior,
) {
    CustomTopAppBar(
        scrollBehavior = scrollBehavior,
        title = stringResource(id = R.string.cart),
        navigationIcon = Icons.Default.KeyboardArrowLeft,
        navOnClick = { navController.popBackStack() },
    )
}

@Preview
@Composable
private fun CartListUIPreview() {
    val dataState = ViewState(
        LoadCartDataState(
            listOf(
                Product(
                    brand = "Samsung",
                    title = "Samsung Z Fold",
                    price = 100,
                    discountPercentage = 20.5,
                    rating = 4.0,
                    thumbnail = "https://cdn.dummyjson.com/product-images/14/2.jpg"
                ),
                Product(
                    brand = "Apple",
                    title = "iPhone 15 pro",
                    price = 799,
                    discountPercentage = 10.5,
                    rating = 4.0,
                    thumbnail = "https://cdn.dummyjson.com/product-images/14/1.jpg"
                )
            ), ApiState.Success
        )
    )
    EasyCartTheme {
        CartListUI(userState = CartListUserState(loadCartDataState = dataState))
    }
}
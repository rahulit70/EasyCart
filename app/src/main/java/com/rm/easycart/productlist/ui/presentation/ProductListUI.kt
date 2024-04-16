package com.rm.easycart.productlist.ui.presentation


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.rm.easycart.R
import com.rm.easycart.core.model.Product
import com.rm.easycart.navigation.Screen
import com.rm.easycart.productlist.event.ProductListUserEvent
import com.rm.easycart.productlist.state.ProductListUserState
import com.rm.easycart.productlist.ui.state.ProductListViewState
import com.rm.easycart.ui.theme.EasyCartTheme
import com.rm.easycart.utils.CustomSnackBar
import com.rm.easycart.utils.CustomTopAppBar
import com.rm.easycart.utils.ShowSnackBar
import kotlinx.coroutines.flow.MutableStateFlow

@Composable
fun ProductListUI(
    navController: NavController = rememberNavController(),
    showSnackBar: ShowSnackBar = { _, _, _ -> },
    userState: ProductListUserState = ProductListUserState(),
    userEvent: ProductListUserEvent = ProductListUserEvent()
) {
    ProductList(
        navController = navController,
        showSnackBar = showSnackBar,
        userState = userState,
        userEvent = userEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductList(
    showSnackBar: ShowSnackBar,
    navController: NavController,
    userState: ProductListUserState,
    userEvent: ProductListUserEvent
) {
    val snackBarState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBarUI(
                navController = navController,
                scrollBehavior = scrollBehavior
            )
        },
        snackbarHost = { CustomSnackBar(snackBarHostState = snackBarState) },
    ) {
        Column(modifier = Modifier.padding(it)) {
            ProductListViewState(showSnackBar, userState)
            ProductListContent(
                navController = navController,
                userState = userState,
                userEvent = userEvent
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBarUI(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController
) {
    CustomTopAppBar(
        scrollBehavior = scrollBehavior,
        navigationIcon = null,
        title = stringResource(id = R.string.app_name),
        actionIcon = Icons.Default.ShoppingCart,
        actionOnClick = { navController.navigate(Screen.CartScreen.route) }
    )
}

@Composable
fun ProductListContent(
    navController: NavController,
    userState: ProductListUserState,
    userEvent: ProductListUserEvent
) {
    val moviePagingItems = userState.productPagingItems.collectAsLazyPagingItems()
    LazyColumn(modifier = Modifier.padding(vertical = 8.dp)) {
        items(moviePagingItems.itemCount) { index ->
            moviePagingItems[index]?.let { product ->
                ProductCardUI(product = product) { id ->
                    navController.navigate(Screen.ProductDetailsScreen.route.plus("/${id}"))
                }
            }
        }
        moviePagingItems.apply {
            when {
                loadState.refresh is LoadState.Loading -> {
                    item { DrawItemLoadingView() }
                }

                loadState.refresh is LoadState.Error -> {
                    //val error = moviePagingItems.loadState.refresh as LoadState.Error
                    item {
                        ItemLoadErrorViewUI(
                            modifier = Modifier.fillMaxWidth(),
                            message = stringResource(id = R.string.error_loading_products)
                        ) { userEvent.getProductList.invoke() }
                    }
                }

                loadState.append is LoadState.Loading -> {
                    item { DrawItemLoadingView() }
                }

                loadState.append is LoadState.Error -> {
                    //val error = moviePagingItems.loadState.append as LoadState.Error
                    item {
                        ItemLoadErrorViewUI(
                            modifier = Modifier.fillMaxWidth(),
                            message = stringResource(id = R.string.error_loading_products)
                        ) { userEvent.getProductList.invoke() }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawItemLoadingView() {
    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
        CircularProgressIndicator(color = Color.LightGray)
    }
}


@Composable
private fun ErrorView(
    modifier: Modifier = Modifier,
    message: String,
    textColor: Color = Color.Red,
    buttonText: String = stringResource(id = R.string.retry),
    retry: () -> Unit,
    image: ImageVector? = null,
    imageDescription: String? = null
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (image != null) {
            Image(
                imageVector = image,
                contentDescription = imageDescription,
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = message,
            color = textColor,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(
            modifier = Modifier,
            onClick = retry
        ) {
            Text(
                text = buttonText,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}

@Composable
private fun ItemLoadErrorViewUI(
    modifier: Modifier = Modifier,
    message: String,
    retry: () -> Unit
) {
    ErrorView(
        modifier = modifier,
        message = message,
        retry = retry,
        image = Icons.Filled.Warning,
        imageDescription = "Error icon"
    )
}


@Preview
@Composable
private fun ProductListUIPreview() {
    val testData = PagingData.from(
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
        )
    )
    val userState = ProductListUserState(productPagingItems = MutableStateFlow(testData))
    EasyCartTheme { ProductListUI(userState = userState) }
}
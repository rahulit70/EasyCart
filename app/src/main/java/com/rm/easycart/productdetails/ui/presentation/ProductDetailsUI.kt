package com.rm.easycart.productdetails.ui.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.rm.easycart.R
import com.rm.easycart.core.model.Product
import com.rm.easycart.navigation.Screen
import com.rm.easycart.productdetails.event.ProductDetailsUserEvent
import com.rm.easycart.productdetails.state.ProductDetailsDataState
import com.rm.easycart.productdetails.state.ProductDetailsUserState
import com.rm.easycart.productdetails.ui.state.ProductDetailsViewState
import com.rm.easycart.ui.theme.EasyCartTheme
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.CoilImageLoader
import com.rm.easycart.utils.CustomSnackBar
import com.rm.easycart.utils.CustomTopAppBar
import com.rm.easycart.utils.ShowSnackBar
import com.rm.easycart.utils.ViewState
import kotlinx.coroutines.launch

@Composable
fun ProductDetailsUI(
    showSnackBar: ShowSnackBar = { _, _, _ -> },
    navController: NavController = rememberNavController(),
    userState: ProductDetailsUserState = ProductDetailsUserState(),
    userEvent: ProductDetailsUserEvent = ProductDetailsUserEvent(),
    productId: Int? = null,
) {
    LaunchedEffect(key1 = Unit) { userEvent.getProductDetailsById.invoke(productId) }
    ProductDetails(showSnackBar, navController, userState, userEvent, productId)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetails(
    showSnackBar: ShowSnackBar,
    navController: NavController,
    userState: ProductDetailsUserState,
    userEvent: ProductDetailsUserEvent,
    productId: Int?
) {
    val snackBarState = remember { SnackbarHostState() }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { ProductDetailsTopAppBar(scrollBehavior, navController, userState) },
        snackbarHost = { CustomSnackBar(snackBarHostState = snackBarState) },
    ) {
        Box(modifier = Modifier.padding(it)) {
            ProductDetailsViewState(
                navController = navController,
                showSnackBar = showSnackBar,
                state = userState,
                onApiFailed = {
                    ProductLoadErrorViewUI(message = stringResource(id = R.string.error_loading_products)) {
                        userEvent.getProductDetailsById.invoke(productId)
                    }
                }
            ) {
                ProductDetailsContent(userState, userEvent)
            }
        }
    }
}

@Composable
fun ProductDetailsContent(userState: ProductDetailsUserState, userEvent: ProductDetailsUserEvent) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        userState.productDetailsDataState.value.product.images?.let {
            ImageSlider(imageUrls = it)
        }
        Divider(thickness = 1.dp)
        ProductCardUI(product = userState.productDetailsDataState.value.product)
        AddToCartButton(onClick = { userEvent.addToCart.invoke(userState.productDetailsDataState.value.product) })
    }

}

@Composable
fun AddToCartButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(contentColor = Color.Blue)
    ) {
        Text(text = "Add To Cart", color = Color.White)
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageSlider(imageUrls: List<String>) {
    val pagerState = rememberPagerState(pageCount = { imageUrls.size })
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .height(400.dp)
    ) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()

        ) { page ->
            CoilImageLoader(
                imageUrl = imageUrls[page],
                contentDesc = stringResource(id = R.string.slider_image),
                modifier = Modifier
                    .fillMaxSize(),
                placeholder = R.drawable.baseline_image_not_supported_24,
                contentScale = ContentScale.Fit
            )
        }

        LazyRow(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            items(imageUrls.size) {
                RadioButton(
                    selected = it == pagerState.currentPage,
                    onClick = { scope.launch { pagerState.scrollToPage(it) } }
                )
            }
        }

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
private fun ProductLoadErrorViewUI(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ProductDetailsTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navController: NavController,
    userState: ProductDetailsUserState,
) {
    CustomTopAppBar(
        scrollBehavior = scrollBehavior,
        title = userState.productDetailsDataState.value.product.title,
        navigationIcon = Icons.Default.KeyboardArrowLeft,
        actionIcon = Icons.Default.ShoppingCart,
        navOnClick = { navController.popBackStack() },
        actionOnClick = { navController.navigate(Screen.CartScreen.route) }
    )
}

@Preview
@Composable
private fun ProductDetailsPageUIPreview() {
    val productDetailsDataState = ViewState(
        ProductDetailsDataState(
            Product(
                brand = "Apple",
                title = "iPhone 15 Pro",
                price = 599,
                discountPercentage = 20.0,
                rating = 4.0,
                stock = 99,
                thumbnail = "https://cdn.dummyjson.com/product-images/14/2.jpg",
                category = "Smart Phone",
                description = "iPhone 15 Pro - US Version "
            ).apply {
                images = listOf(
                    "https://cdn.dummyjson.com/product-images/14/2.jpg",
                    "https://cdn.dummyjson.com/product-images/14/2.jpg",
                    "https://cdn.dummyjson.com/product-images/14/2.jpg",
                    "https://cdn.dummyjson.com/product-images/14/2.jpg",
                )
            }, ApiState.Success
        )
    )
    val state = ProductDetailsUserState(productDetailsDataState = productDetailsDataState)
    EasyCartTheme { ProductDetailsUI(userState = state) }
}
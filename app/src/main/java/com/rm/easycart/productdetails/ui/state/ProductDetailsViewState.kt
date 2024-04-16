package com.rm.easycart.productdetails.ui.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.rm.easycart.R
import com.rm.easycart.navigation.Screen
import com.rm.easycart.productdetails.state.ProductDetailsUserState
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.AppConstants.Companion.API_FAILED
import com.rm.easycart.utils.AppConstants.Companion.API_SUCCESS
import com.rm.easycart.utils.ProgressDialog
import com.rm.easycart.utils.ShowSnackBar

@Composable
fun ProductDetailsViewState(
    navController: NavController,
    showSnackBar: ShowSnackBar,
    state: ProductDetailsUserState,
    onApiFailed: @Composable () -> Unit,
    drawContent: @Composable () -> Unit
) {
    val signupDataState = state.productDetailsDataState.value
    signupDataState.apiState.ResolveState(
        loading = { ProgressDialog {} },
        success = { drawContent.invoke() },
        error = { onApiFailed.invoke() }
    )

    state.addToCartDataState.value.apiState.ResolveState(
        loading = { ProgressDialog {} },
        success = {
            showSnackBar(
                stringResource(id = R.string.add_to_cart_success),
                API_SUCCESS,
                SnackbarDuration.Short
            )
            state.addToCartDataState.value.apiState = ApiState.Initial
            navController.popBackStack()
            navController.navigate(Screen.CartScreen.route)
        },
        error = {
            showSnackBar(
                stringResource(id = R.string.database_error),
                API_FAILED,
                SnackbarDuration.Short
            )
            state.addToCartDataState.value.apiState = ApiState.Initial
        }
    )
}
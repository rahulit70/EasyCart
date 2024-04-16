package com.rm.easycart.productlist.ui.state

import androidx.compose.material3.SnackbarDuration
import androidx.compose.runtime.Composable
import com.rm.easycart.productlist.state.ProductListUserState
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.AppConstants.Companion.API_FAILED
import com.rm.easycart.utils.ProgressDialog
import com.rm.easycart.utils.ShowSnackBar

@Composable
fun ProductListViewState(
    showSnackBar: ShowSnackBar,
    state: ProductListUserState
) {
    val productListDataState = state.dataState.value
    productListDataState.apiState.ResolveState(
        loading = { ProgressDialog {} },
        success = { },
        error = {
            showSnackBar(productListDataState.message, API_FAILED, SnackbarDuration.Short)
            state.dataState.value.apiState = ApiState.Initial
        }
    )

}


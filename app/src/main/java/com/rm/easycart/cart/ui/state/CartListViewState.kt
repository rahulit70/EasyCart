package com.rm.easycart.cart.ui.state

import androidx.compose.runtime.Composable
import com.rm.easycart.cart.state.CartListUserState
import com.rm.easycart.utils.ProgressDialog

@Composable
fun CartListViewState(
    state: CartListUserState,
    onCartEmpty: @Composable () -> Unit,
    drawContent: @Composable () -> Unit
) {
    state.loadCartDataState.value.apiState.ResolveState(
        loading = { ProgressDialog {} },
        success = { drawContent.invoke() },
        error = { onCartEmpty.invoke() }
    )

}
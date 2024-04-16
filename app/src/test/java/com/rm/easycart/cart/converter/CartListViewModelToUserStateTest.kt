package com.rm.easycart.cart.converter

import androidx.compose.runtime.State
import com.rm.easycart.cart.state.LoadCartDataState
import com.rm.easycart.cart.viewmodel.CartListViewModel
import com.rm.easycart.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Test

class CartListViewModelToUserStateTest {

    @Test
    fun testConvert() {
        // Mocking the CartListViewModel
        val cartListViewModel = mockk<CartListViewModel>()

        // Mocking the loadCartDataState value
        val mockViewState = ViewState(LoadCartDataState())
        val mockState: State<ViewState<LoadCartDataState>> = mockk()
        every { cartListViewModel.loadCartDataState } returns mockState
        every { mockState.value } returns mockViewState

        // Creating an instance of the converter
        val converter = CartListViewModelToUserState()

        // Calling the convert method
        val result = converter.convert(cartListViewModel)

        // Asserting the result
        assertEquals(mockViewState.value, result.loadCartDataState.value)
    }
}

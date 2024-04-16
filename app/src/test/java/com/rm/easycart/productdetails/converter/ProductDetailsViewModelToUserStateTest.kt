package com.rm.easycart.productdetails.converter

import androidx.compose.runtime.State
import com.rm.easycart.productdetails.state.AddToCartDataState
import com.rm.easycart.productdetails.state.ProductDetailsDataState
import com.rm.easycart.productdetails.viewmodel.ProductDetailsViewModel
import com.rm.easycart.utils.ViewState
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test

class ProductDetailsViewModelToUserStateTest {

    @Test
    fun testConvert() {
        // Mocking the ProductDetailsViewModel
        val productDetailsViewModel = mockk<ProductDetailsViewModel>()

        // Mocking the productDetailsDataState value
        val mockViewState = ViewState(ProductDetailsDataState())
        val mockState: State<ViewState<ProductDetailsDataState>> = mockk()
        every { productDetailsViewModel.productDetailsDataState } returns mockState
        every { mockState.value } returns mockViewState

        // Mocking the AddToCartDataState value
        val mockViewState1 = ViewState(AddToCartDataState())
        val mockState1: State<ViewState<AddToCartDataState>> = mockk()
        every { productDetailsViewModel.addToCartDataState } returns mockState1
        every { mockState1.value } returns mockViewState1

        // Creating an instance of the converter
        val converter = ProductDetailsViewModelToUserState()

        // Calling the convert method
        val result = converter.convert(productDetailsViewModel)

        // Asserting the result
        Assert.assertEquals(mockViewState.value, result.productDetailsDataState.value)
        Assert.assertEquals(mockViewState1.value, result.addToCartDataState.value)

    }
}
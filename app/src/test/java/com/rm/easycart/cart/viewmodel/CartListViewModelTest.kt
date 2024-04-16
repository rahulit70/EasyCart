package com.rm.easycart.cart.viewmodel

import com.rm.easycart.cart.state.LoadCartDataState
import com.rm.easycart.cart.usecase.LoadCartUseCase
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class CartListViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testLoadCart() = runTest {

        val products = listOf(
            Product(brand = "P1", title = "T1"),
            Product(brand = "P2", title = "T2")
        )
        // Mocking the LoadCartUseCase
        val loadCartUseCase = mockk<LoadCartUseCase>()

        // Mocking the response of loadCartUseCase
        val mockResponse: Flow<Resource<List<Product>>> =
            flowOf(Resource.Success(products, "SUCCESS"))
        coEvery { loadCartUseCase.invoke() } returns mockResponse

        // Creating an instance of the CartListViewModel
        val viewModel = CartListViewModel(loadCartUseCase)

        // Calling the loadCart method
        viewModel.loadCart()

        // Advance the coroutine dispatcher to allow the suspending function to execute
        advanceUntilIdle()

        // Ensure the coroutine is launched
        coVerify { loadCartUseCase.invoke() }



        // Asserting the value of loadCartDataState after loadCart method execution
        val expectedState = LoadCartDataState(apiState = ApiState.Success, products = products)
        assertEquals(expectedState, viewModel.loadCartDataState.value.value)
    }
}

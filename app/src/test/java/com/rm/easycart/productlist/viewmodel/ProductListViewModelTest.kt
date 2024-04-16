package com.rm.easycart.productlist.viewmodel

import androidx.paging.PagingData
import com.rm.easycart.MainDispatcherRule
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.productdetails.state.ProductDetailsDataState
import com.rm.easycart.productlist.state.ProductListDataState
import com.rm.easycart.productlist.usecase.GetProductListUseCase
import com.rm.easycart.utils.ApiState
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private  val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun `get product list test success`() = runTest {

        // Mocking the GetProductListUseCase
        val getProductListUseCase = mockk<GetProductListUseCase>()

        // Define the dummy PagingData
        val dummyProductList = listOf(
            Product(id = 1, title = "p1"),
            Product(id = 2, title = "p2"),
        )

        val dummyPagingData = PagingData.from(dummyProductList)

        // Mocking the response of getProductListUseCase
        val mockResponse: Flow<PagingData<Product>> =
            flowOf(dummyPagingData)
        coEvery { getProductListUseCase.invoke() } returns mockResponse

        // Creating an instance of the ProductListViewModel
        val viewModel = ProductListViewModel(
            getProductListUseCase = getProductListUseCase
        )

        // Calling the getProductsList method
        viewModel.getProductsList()

        // Advance the coroutine dispatcher to allow the suspending function to execute
        advanceUntilIdle()

        // Ensure the coroutine is launched
        coVerify { getProductListUseCase.invoke() }


      //  val productStateExpected : MutableStateFlow<PagingData<Product>> = MutableStateFlow(value =dummyPagingData)

       // coVerify { viewModel.updateState(dummyPagingData) }
    }

/*    @Test
    fun `update state success`() = runTest {

        // Mocking the GetProductListUseCase
        val getProductListUseCase = mockk<GetProductListUseCase>()

        // Define the dummy PagingData
        val dummyProductList = listOf(
            Product(id = 1, title = "p1"),
            Product(id = 2, title = "p2"),
        )

        val dummyPagingData = PagingData.from(dummyProductList)


        // Creating an instance of the ProductListViewModel
        val viewModel = ProductListViewModel(
            getProductListUseCase = getProductListUseCase
        )

        // Calling the updateState method
        viewModel.updateState(dummyPagingData)


        val productStateExpected : MutableStateFlow<PagingData<Product>> = MutableStateFlow(value =dummyPagingData)

       assertEquals(viewModel.productState.value,productStateExpected.value)
    }*/
}

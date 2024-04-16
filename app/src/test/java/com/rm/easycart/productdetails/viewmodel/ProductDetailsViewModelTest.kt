package com.rm.easycart.productdetails.viewmodel

import com.rm.easycart.MainCoroutineRule
import com.rm.easycart.MainDispatcherRule
import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.state.AddToCartDataState
import com.rm.easycart.productdetails.state.ProductDetailsDataState
import com.rm.easycart.productdetails.usecase.AddToCartUseCase
import com.rm.easycart.productdetails.usecase.GetProductDetailsByIdUseCase
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.TestCoroutineDispatcher
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
class ProductDetailsViewModelTest {

/*    @OptIn(DelicateCoroutinesApi::class)
    private val mainThreadSurrogate = newSingleThreadContext("UI thread")*/

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
    fun `add to cart test success`() = runTest {

        // Mocking product and productID
        val product = Product(brand = "P1", title = "T1")
        val productID:Long = 1

        // Mocking the GetProductDetailsByIdUseCase
        val getProductDetailsByIdUseCase = mockk<GetProductDetailsByIdUseCase>()

        // Mocking the AddToCartUseCase
        val addToCartUseCase = mockk<AddToCartUseCase>()


        // Mocking the response of addToCart
        val mockResponse: Flow<Resource<Long>> =
            flowOf(Resource.Success(productID, AppConstants.API_SUCCESS))
        coEvery { addToCartUseCase.invoke(product = product) } returns mockResponse

        // Creating an instance of the ProductDetailsViewModel
        val viewModel = ProductDetailsViewModel(
            getProductDetailsById = getProductDetailsByIdUseCase,
            addToCartUseCase = addToCartUseCase
        )

        // Calling the addToCart method
        viewModel.addToCart(product = product)

        // Ensure the coroutine is launched
        coVerify { addToCartUseCase.invoke(product = product) }


        // Asserting the value of addToCartDataState after addToCart method execution
        val expectedState = AddToCartDataState(apiState = ApiState.Success, id = productID)


        // Await for the value to be updated
        advanceUntilIdle()


        // Assert the value
        assertEquals(expectedState, viewModel.addToCartDataState.value.value)
    }

    @Test
    fun `add to cart test error`() = runTest {

        // Mocking product and productID
        val product = Product(brand = "P1", title = "T1")
        val productID:Long = 0

        // Mocking the GetProductDetailsByIdUseCase
        val getProductDetailsByIdUseCase = mockk<GetProductDetailsByIdUseCase>()

        // Mocking the AddToCartUseCase
        val addToCartUseCase = mockk<AddToCartUseCase>()


        // Mocking the response of addToCart
        val mockResponse: Flow<Resource<Long>> =
            flowOf(Resource.Error(productID, AppConstants.API_FAILED))
        coEvery { addToCartUseCase.invoke(product = product) } returns mockResponse

        // Creating an instance of the ProductDetailsViewModel
        val viewModel = ProductDetailsViewModel(
            getProductDetailsById = getProductDetailsByIdUseCase,
            addToCartUseCase = addToCartUseCase
        )

        // Calling the addToCart method
        viewModel.addToCart(product = product)

        // Ensure the coroutine is launched
        coVerify { addToCartUseCase.invoke(product = product) }


        // Asserting the value of addToCartDataState after addToCart method execution
        val expectedState = AddToCartDataState(apiState = ApiState.Error, id = productID)


        // Await for the value to be updated
        advanceUntilIdle()


        // Assert the value
        assertEquals(expectedState, viewModel.addToCartDataState.value.value)
    }

    @Test
    fun `get product details by ID test success`() = runTest {

        val product = Product(id = 1, brand = "P1", title = "T1")
        // Mocking the GetProductDetailsByIdUseCase
        val getProductDetailsByIdUseCase = mockk<GetProductDetailsByIdUseCase>()

        // Mocking the AddToCartUseCase
        val addToCartUseCase = mockk<AddToCartUseCase>()
        val productID = 1

        // Mocking the response of GetProductDetailsByIdUseCase
        val mockResponse: Flow<Resource<Product>> =
            flowOf(Resource.Success(product, AppConstants.API_SUCCESS))
        coEvery { getProductDetailsByIdUseCase.invoke(productID) } returns mockResponse

        // Creating an instance of the ProductDetailsViewModel
        val viewModel = ProductDetailsViewModel(
            getProductDetailsById = getProductDetailsByIdUseCase,
            addToCartUseCase = addToCartUseCase
        )

        // Calling the getProductDetailsByID method
        viewModel.getProductDetailsByID(productId = productID)

        // Advance the coroutine dispatcher to allow the suspending function to execute
        advanceUntilIdle()

        // Ensure the coroutine is launched
        coVerify { getProductDetailsByIdUseCase.invoke(productId = productID) }



        // Asserting the value of ProductDetailsDataState after getProductDetailsByID method execution
        val expectedState = ProductDetailsDataState(apiState = ApiState.Success, product = product)
        assertEquals(expectedState, viewModel.productDetailsDataState.value.value)
    }

    @Test
    fun `get product details by ID test error`() = runTest {

        val product = Product()
        // Mocking the GetProductDetailsByIdUseCase
        val getProductDetailsByIdUseCase = mockk<GetProductDetailsByIdUseCase>()

        // Mocking the AddToCartUseCase
        val addToCartUseCase = mockk<AddToCartUseCase>()
        val productID = 1

        // Mocking the response of GetProductDetailsByIdUseCase
        val mockResponse: Flow<Resource<Product>> =
            flowOf(Resource.Error(product, AppConstants.API_FAILED))
        coEvery { getProductDetailsByIdUseCase.invoke(productID) } returns mockResponse

        // Creating an instance of the ProductDetailsViewModel
        val viewModel = ProductDetailsViewModel(
            getProductDetailsById = getProductDetailsByIdUseCase,
            addToCartUseCase = addToCartUseCase
        )

        // Calling the getProductDetailsByID method
        viewModel.getProductDetailsByID(productId = productID)

        // Advance the coroutine dispatcher to allow the suspending function to execute
        advanceUntilIdle()

        // Ensure the coroutine is launched
        coVerify { getProductDetailsByIdUseCase.invoke(productId = productID) }



        // Asserting the value of ProductDetailsDataState after getProductDetailsByID method execution
        val expectedState = ProductDetailsDataState(apiState = ApiState.Error, product = product)
        assertEquals(expectedState, viewModel.productDetailsDataState.value.value)
    }

}

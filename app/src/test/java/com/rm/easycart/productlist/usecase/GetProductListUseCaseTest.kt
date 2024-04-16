package com.rm.easycart.productlist.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.productlist.repository.ProductListRepoImpl
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import retrofit2.Response

class GetProductListUseCaseTest {

    private val productListRepo = mockk<ProductListRepoImpl>(relaxed = true)
    private val getProductListUseCase = GetProductListUseCaseImpl(productListRepo)


    @Test
    fun `get product list test success`() = runTest {
        // Define the dummy PagingData
        val dummyProductList = listOf(
            Product(id = 1, title = "p1"),
            Product(id = 2, title = "p2"),
        )
        // Mock your dependencies
        val productsResponse = ProductsResponse(products = dummyProductList)
        val expectedResponse: Response<ProductsResponse> = Response.success(productsResponse)

        // Mock the behavior of productListRepo to return success response
        coEvery { productListRepo.invoke(any()) } returns expectedResponse


        // Call the method under test
        val result = getProductListUseCase.invoke(0)

        // Verify the result
        assertEquals(expectedResponse, result)
    }

    @Test
    fun `get product list test failure`() = runTest {
        // Mock your dependencies
        val errorMessage = "Failed to fetch products"
        val expectedResponse: Response<ProductsResponse> =
            Response.error(404, errorMessage.toResponseBody(null))

        // Mock the behavior of productListRepo to return failure response
        coEvery { productListRepo.invoke(any()) } returns expectedResponse


        // Call the method under test
        val result = getProductListUseCase.invoke(1)

        // Verify that the result is a failure response
        assertTrue(!result.isSuccessful)
    }


}



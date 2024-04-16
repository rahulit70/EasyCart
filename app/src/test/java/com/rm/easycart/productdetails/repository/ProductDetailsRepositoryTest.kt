package com.rm.easycart.productdetails.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.core.network.ApiService
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test
import retrofit2.Response

class ProductDetailsRepositoryTest {


    @Test
    fun testGetProductByID_Success() = runTest {
        // Mocking product
        val product = Product(id = 1, title = "Product 1", price = 10)
        val id = 1

        // Mocking the ApiService
        val apiService = mockk<ApiService>(relaxed = true)
        val repository = ProductDetailsRepositoryImpl(apiService)



        coEvery { apiService.getProductById(id) } returns Response.success(product)


        // Calling the get product by  id method
        val flowResult: Flow<Resource<Product>> = repository.invoke(id)

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading & success
        assertEquals(AppConstants.API_SUCCESS, result[1].message)
        assertEquals(product, result[1].data)
        assertTrue(result[1] is Resource.Success)
        assertTrue(result[0] is Resource.Loading)
    }


    @Test
    fun testGetProductByID_Failure() = runTest {
        // Mocking product
        val productId: Int = -1

        // Mocking the ApiService
        val apiService = mockk<ApiService>(relaxed = true)
        val repository = ProductDetailsRepositoryImpl(apiService)


        // Mocking API response with error message
        val errorBody = "{\"error\": \"Product not found\"}"
        val errorResponseBody = errorBody.toResponseBody("application/json".toMediaTypeOrNull())
        val errorResponse = Response.error<Product>(400, errorResponseBody)
        coEvery { apiService.getProductById(productId) } returns errorResponse


        // Calling the get product by  id method
        val flowResult: Flow<Resource<Product>> = repository.invoke(productId)

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading & success
        assertNotNull(result[1].message)
        assertEquals(null, result[1].data)
        assertTrue(result[1] is Resource.Error)
        assertTrue(result[0] is Resource.Loading)
    }

}

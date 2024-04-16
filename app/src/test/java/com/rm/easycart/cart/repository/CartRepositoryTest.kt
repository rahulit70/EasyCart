package com.rm.easycart.cart.repository

import com.rm.easycart.core.db.ProductDAO
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.AppConstants
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class CartRepositoryTest {


    @Test
    fun testGetAllCartItems_Success() = runTest {
        // Mocking the list of products
        val productList = listOf(
            Product(id = 1, title = "Product 1", price = 10),
            Product(id = 1, title = "Product 2", price = 11)
        )
        // Mocking the ProductDAO
        val productDAO = mockk<ProductDAO>(relaxed = true)
        val repository = CartRepositoryImpl(productDAO)



        coEvery { productDAO.getProducts() } returns productList


        // Calling the getAllCartItems method
        val flowResult: Flow<Resource<List<Product>>> = repository.getAllCartItems()

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading & success
        assertEquals(AppConstants.API_SUCCESS, result[1].message)
        assertEquals(2, result[1].data?.size)
        assertTrue( result[1] is Resource.Success)
        assertTrue( result[0] is Resource.Loading)
    }

    @Test
    fun testGetAllCartItems_Empty() = runTest {
        // Mocking the list of products
        val productList = emptyList<Product>()
        // Mocking the ProductDAO
        val productDAO = mockk<ProductDAO>(relaxed = true)
        val repository = CartRepositoryImpl(productDAO)


        coEvery { productDAO.getProducts() } returns productList


        // Calling the getAllCartItems method
        val flowResult: Flow<Resource<List<Product>>> = repository.getAllCartItems()

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading and error
        assertEquals(AppConstants.EMPTY_CART, result[1].message)
        assertEquals(null, result[1].data)
        assertTrue( result[1] is Resource.Error)
        assertTrue( result[0] is Resource.Loading)

    }
}

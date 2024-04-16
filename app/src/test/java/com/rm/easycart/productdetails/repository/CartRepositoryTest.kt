package com.rm.easycart.productdetails.repository

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
    fun testAddProduct_Success() = runTest {
        // Mocking product
        val product = Product( title = "Product 1", price = 10)
        val id:Long=1

        // Mocking the ProductDAO
        val productDAO = mockk<ProductDAO>(relaxed = true)
        val repository = CartRepositoryImpl(productDAO)



        coEvery { productDAO.insertProduct(product) } returns id


        // Calling the insert product method
        val flowResult: Flow<Resource<Long>> = repository.invoke(product)

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading & success
        assertEquals(AppConstants.DB_SUCCESS, result[1].message)
        assertEquals(id, result[1].data)
        assertTrue( result[1] is Resource.Success)
        assertTrue( result[0] is Resource.Loading)
    }


    @Test
    fun testAddProduct_Fail() = runTest {
        // Mocking product
        val product = Product( title = "Product 1", price = 10)
        val id:Long=-1

        // Mocking the ProductDAO
        val productDAO = mockk<ProductDAO>(relaxed = true)
        val repository = CartRepositoryImpl(productDAO)



        coEvery { productDAO.insertProduct(product) } returns id


        // Calling the insert product method
        val flowResult: Flow<Resource<Long>> = repository.invoke(product)

        // Collecting flow results
        val result = flowResult.toList()

        // Asserting the flow results
        assertEquals(2, result.size)// expecting loading & Error
        assertEquals(AppConstants.DB_ERROR, result[1].message)
        assertEquals(null, result[1].data)
        assertTrue( result[1] is Resource.Error)
        assertTrue( result[0] is Resource.Loading)
    }

}

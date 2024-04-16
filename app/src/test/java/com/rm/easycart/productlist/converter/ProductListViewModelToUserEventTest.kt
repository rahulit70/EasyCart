package com.rm.easycart.productlist.converter

import com.rm.easycart.productlist.viewmodel.ProductListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ProductListViewModelToUserEventTest {


    @Test
    fun `testConvert get product list`() = runTest {
        // Mocking the ProductListViewModel
        val productListViewModel = mockk<ProductListViewModel>(relaxed = true)

        // Stubbing the behavior of getProductsList method
        coEvery { productListViewModel.getProductsList() } returns Unit // Returning a mock Job object

        // Creating an instance of the converter
        val converter = ProductListViewModelToUserEvent()

        // Calling the convert method
        val result = converter.convert(productListViewModel)

        // Asserting the result
        Assert.assertEquals(Unit::class.java, result.getProductList.invoke()::class.java)
    }


}
package com.rm.easycart.productdetails.converter


import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.viewmodel.ProductDetailsViewModel
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Job
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Test

class ProductDetailsViewModelToUserEventTest {


    @Test
    fun `testConvert get product by id` () = runTest {
        // Mocking the ProductDetailsViewModel
        val productDetailsViewModel = mockk<ProductDetailsViewModel>(relaxed = true)

        // Stubbing the behavior of getProductDetailsByID method
        coEvery { productDetailsViewModel.getProductDetailsByID(1) } returns Job() // Returning a mock Job object

        // Creating an instance of the converter
        val converter = ProductDetailsViewModelToUserEvent()

        // Calling the convert method
        val result = converter.convert(productDetailsViewModel)

        // Asserting the result
        Assert.assertEquals(Unit::class.java, result.getProductDetailsById.invoke(1)::class.java)
    }

    @Test
    fun `testConvert get add to cart` () = runTest {
        // Mocking the ProductDetailsViewModel
        val productDetailsViewModel = mockk<ProductDetailsViewModel>(relaxed = true)

        // Stubbing the behavior of addToCart method
        coEvery { productDetailsViewModel.addToCart(Product(id=1)) } returns Job() // Returning a mock Job object

        // Creating an instance of the converter
        val converter = ProductDetailsViewModelToUserEvent()

        // Calling the convert method
        val result = converter.convert(productDetailsViewModel)

        // Asserting the result
        Assert.assertEquals(Unit::class.java, result.addToCart.invoke(Product(id=1))::class.java)
    }
}
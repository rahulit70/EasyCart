package com.rm.easycart.productdetails.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.repository.CartRepository
import com.rm.easycart.productdetails.repository.CartRepositoryImpl
import com.rm.easycart.productdetails.repository.ProductDetailsRepositoryImpl
import com.rm.easycart.utils.AppConstants.Companion.API_SUCCESS
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class AddToCartUseCaseTest {

    private val cartRepository = mockk<CartRepositoryImpl>(relaxed = true)
    private val addToCartUseCase = AddToCartUseCaseImpl(cartRepository)

    @Test
    fun `add product to cart test success`() {
        //arrange
        val product = Product( brand = "P1", title = "T1")
        val productId:Long=1

        coEvery { cartRepository.invoke(product) } returns flowOf(
            Resource.Success(
                productId,
                API_SUCCESS
            )
        )

        runBlocking {
            addToCartUseCase.invoke(product).collect {
                Assert.assertTrue(it is Resource.Success)
                Assert.assertTrue(it.data == productId)
            }
        }


    }

    @Test
    fun `add product to cart test failure`() {
        //arrange
        val product = Product( brand = "P1", title = "T1")

        coEvery { cartRepository.invoke(product) } returns flowOf(
            Resource.Error(
                null,
                "ERROR"
            )
        )

        runBlocking {
            addToCartUseCase.invoke(product).collect {
                Assert.assertTrue(it is Resource.Error)
                Assert.assertTrue(it.data == null)
            }
        }


    }

}
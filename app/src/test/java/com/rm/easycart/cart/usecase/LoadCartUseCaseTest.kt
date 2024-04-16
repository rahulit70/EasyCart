package com.rm.easycart.cart.usecase

import com.rm.easycart.cart.repository.CartRepositoryImpl
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class LoadCartUseCaseTest {

    private val cartRepository = mockk<CartRepositoryImpl>(relaxed = true)
    private val loadCartUseCase = LoadCartUseCaseImpl(cartRepository)

    @Test
    fun `load cart test success`() {
        //arrange
        val products = listOf(
            Product(brand = "P1", title = "T1"),
            Product(brand = "P2", title = "T2")
        )
        coEvery { cartRepository.getAllCartItems() } returns flowOf(
            Resource.Success(
                products,
                "SUCCESS"
            )
        )

        runBlocking {
            loadCartUseCase.invoke().collect {
                Assert.assertTrue(it is Resource.Success)
                Assert.assertTrue(it.data?.size == 2)
            }
        }


    }

    @Test
    fun `load cart test failure`() {
        //arrange

        coEvery { cartRepository.getAllCartItems() } returns flowOf(
            Resource.Error(
                null,
                "ERROR"
            )
        )

        runBlocking {
            loadCartUseCase.invoke().collect {
                Assert.assertTrue(it is Resource.Error)
                Assert.assertTrue(it.data== null)
            }
        }


    }

}
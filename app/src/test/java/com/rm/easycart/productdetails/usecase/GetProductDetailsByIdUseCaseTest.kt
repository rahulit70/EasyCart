package com.rm.easycart.productdetails.usecase

import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.repository.ProductDetailsRepositoryImpl
import com.rm.easycart.utils.AppConstants.Companion.API_SUCCESS
import com.rm.easycart.utils.Resource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class GetProductDetailsByIdUseCaseTest {

    private val productDetailsRepository = mockk<ProductDetailsRepositoryImpl>(relaxed = true)
    private val getProductDetailsByIdUseCase = GetProductDetailsByIdUseCaseImpl(productDetailsRepository)

    @Test
    fun `get product by id test success`() {
        //arrange
        val product = Product(id = 1, brand = "P1", title = "T1")

        val productId = 1
        coEvery { productDetailsRepository.invoke(productId) } returns flowOf(
            Resource.Success(
                product,
                API_SUCCESS
            )
        )

        runBlocking {
            getProductDetailsByIdUseCase.invoke(productId).collect {
                Assert.assertTrue(it is Resource.Success)
                Assert.assertTrue(it.data == product)
            }
        }


    }

    @Test
    fun `get product by id  test failure`() {
        //arrange
        val productId = 1

        coEvery { productDetailsRepository.invoke(productId) } returns flowOf(
            Resource.Error(
                null,
                "ERROR"
            )
        )

        runBlocking {
            getProductDetailsByIdUseCase.invoke(productId).collect {
                Assert.assertTrue(it is Resource.Error)
                Assert.assertTrue(it.data == null)
            }
        }


    }

}
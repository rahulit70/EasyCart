package com.rm.easycart.productlist.usecase

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.productlist.repository.ProductListRepoImpl
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

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
        val dummyPagingData = PagingData.from(dummyProductList)

        // Mock the behavior of ProductListRepo
        coEvery { productListRepo.invoke() } returns flowOf(dummyPagingData)


        // Call the use case method
        //val resultFlow: Flow<PagingData<Product>> = getProductListUseCase.invoke()


        // Verify that ProductListRepo's invoke() was called
        coVerify { productListRepo.invoke() }


    }


}



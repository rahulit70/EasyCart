package com.rm.easycart.productlist.viewmodel

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.productlist.usecase.GetProductListUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {


    private lateinit var viewModel: ProductListViewModel
    private lateinit var productListUseCase: GetProductListUseCase

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Default)
        productListUseCase = mockk()
        viewModel = ProductListViewModel(productListUseCase)
    }

    @Test
    fun getProductListTest() {
        runBlocking {
            val data = ProductsResponse(
                listOf(
                    Product(
                        id = 1,
                        title = "P1",
                        price = 111,
                    ),
                    Product(
                        id = 2,
                        title = "P2",
                        price = 999,
                    )
                )
            )

            coEvery { productListUseCase.invoke(any()) } returns Response.success(data)


             viewModel.getProductsList()

            assert(viewModel.productState.value != PagingData.empty<Product>())

        }
    }
}

package com.rm.easycart.productlist.repository

import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.core.network.ApiService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import retrofit2.Response


class ProductListRepoImplTest {

    private lateinit var apiService: ApiService
    private lateinit var productListRepoImpl: ProductListRepoImpl

    @Before
    fun setup() {
        apiService = mockk()
        productListRepoImpl = ProductListRepoImpl(apiService)
    }

    @Test
    fun `invoke returns ProductResponse`() = runTest {
        val fakeProducts = listOf(
            Product(id = 1, title = "Product 1"),
            Product(id = 2, title = "Product 2")
        )
        val fakeResponse = Response.success(ProductsResponse(fakeProducts))

        coEvery { apiService.getProducts(any(), any()) } returns fakeResponse



        val result = productListRepoImpl.invoke(0)

        assertEquals(fakeProducts, result.body()?.products)
    }
}

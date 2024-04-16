package com.rm.easycart.productlist.repository
import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.core.network.ApiService
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductListRepoImplTest {
    // TODO: resolve issue with log method

    /*private lateinit var apiService: ApiService
    private lateinit var productListRepoImpl: ProductListRepoImpl

    @Before
    fun setup() {
        apiService = mockk()
        productListRepoImpl = ProductListRepoImpl(apiService)
    }

    @Test
    fun `invoke returns Flow of PagingData of Products`() = runTest {
        val fakeProducts = listOf(
            Product(id = 1, title = "Product 1"),
            Product(id = 2, title = "Product 2")
        )
        val fakePagingData = PagingData.from(fakeProducts)

        coEvery { apiService.getProducts(any(), any()) } returns Response.success(ProductsResponse(products = fakeProducts, skip = 0))

        coEvery { productListRepoImpl.invoke().first() } returns fakePagingData

        val result = productListRepoImpl.invoke().first()

        assertEquals(fakePagingData, result)
    }*/
}

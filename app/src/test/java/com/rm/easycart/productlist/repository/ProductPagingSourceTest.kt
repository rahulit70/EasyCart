package com.rm.easycart.productlist.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rm.easycart.core.model.Product
import com.rm.easycart.core.model.ProductsResponse
import com.rm.easycart.core.network.ApiService
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class ProductPagingSourceTest {


    private lateinit var apiService: ApiService
    private lateinit var pagingSource: ProductPagingSource

    @Before
    fun setup() {
        apiService = mockk()
        pagingSource = ProductPagingSource(apiService)
    }

    @Test
    fun `load returns LoadResult Page when successful`() = runBlocking {
        val fakeProducts = listOf(
            Product(id = 1, title = "Product 1"),
            Product(id = 2, title = "Product 2")
        )
        val fakeResponse = Response.success(ProductsResponse(products = fakeProducts, skip = 0))
        coEvery { apiService.getProducts(10, 0) } returns fakeResponse

        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 10,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        assertNotNull(result)
        assertTrue(result is PagingSource.LoadResult.Page)
        val pageResult = result as PagingSource.LoadResult.Page
        assertEquals(fakeProducts, pageResult.data)
        assertEquals(null, pageResult.prevKey)
        assertEquals(10, pageResult.nextKey)
    }

    @Test
    fun `load returns LoadResult Error when exception occurs`() = runBlocking {
        coEvery { apiService.getProducts(10, 0) } throws RuntimeException("Network Error")

        val loadParams = PagingSource.LoadParams.Refresh<Int>(
            key = null,
            loadSize = 10,
            placeholdersEnabled = false
        )
        val result = pagingSource.load(loadParams)

        assertNotNull(result)
        assertTrue(result is PagingSource.LoadResult.Error)
    }

    @Test
    fun `getRefreshKey returns anchorPosition`() {
        val pagingState: PagingState<Int, Product> = mockk()
        val anchorPosition = 10
        every { pagingState.anchorPosition } returns anchorPosition

        val refreshKey = pagingSource.getRefreshKey(pagingState)

        assertEquals(anchorPosition, refreshKey)
    }
}

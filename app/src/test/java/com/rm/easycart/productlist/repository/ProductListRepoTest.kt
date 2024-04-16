package com.rm.easycart.productlist.repository


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

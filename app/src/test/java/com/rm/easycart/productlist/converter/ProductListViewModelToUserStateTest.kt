package com.rm.easycart.productlist.converter

import androidx.paging.PagingData
import com.rm.easycart.core.model.Product
import com.rm.easycart.productlist.viewmodel.ProductListViewModel
import io.mockk.every
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Test

class ProductListViewModelToUserStateTest {

    @Test
    fun `test convert`() {
        // Mock input
        val inputViewModel = mockk<ProductListViewModel>()

        // Mock productState
        val mockPagingData =
            PagingData.from(
                listOf(
                    Product(1, "Product 1"),
                    Product(2, "Product 2")
                )
            )
        val mockStateFlow = MutableStateFlow(mockPagingData)

        // Stubbing the behavior of inputViewModel
        every { inputViewModel.productState } returns mockStateFlow

        // Call the method to test
        val converter = ProductListViewModelToUserState()
        val result = converter.convert(inputViewModel)

        // Assert the result
        assertEquals(mockStateFlow, result.productPagingItems)
    }
}
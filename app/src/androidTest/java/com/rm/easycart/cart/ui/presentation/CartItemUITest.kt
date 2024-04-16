package com.rm.easycart.cart.ui.presentation

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.rm.easycart.core.model.Product
import io.mockk.every
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test

class CartItemUITest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cartItemUI_Rendering() {
        // Mock the Product object
        val product = mockk<Product> {
            every { title } returns "iPhone 15 Pro"
            every { rating } returns 4.0
            every { price } returns 499
            every { discountPercentage } returns 19.0
            every { thumbnail } returns "https://cdn.dummyjson.com/product-images/1/thumbnail.jpg"
        }

        // Render the CartItemUI composable with the mocked Product
        composeTestRule.setContent {
            CartItemUI(product = product)
        }

        // Verify if the UI elements are rendered correctly
        composeTestRule
            .onNodeWithText(text = "iPhone 15 Pro")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(text = "₹499")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText(text = "(19.0% Off)")
            .assertIsDisplayed()

    }
}

package com.rm.easycart.cart.ui.presentation

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import com.rm.easycart.cart.state.CartListUserState
import com.rm.easycart.cart.state.LoadCartDataState
import com.rm.easycart.core.model.Product
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.ViewState
import org.junit.Rule
import org.junit.Test

class CartListContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun cartListContent_Rendering() {
        // Create test data for LoadCartDataState
        val products = listOf(
            Product(
                brand = "Samsung",
                title = "Samsung Z Fold",
                price = 100,
                discountPercentage = 20.5,
                rating = 4.0,
                thumbnail = "https://cdn.dummyjson.com/product-images/14/2.jpg"
            ),
            Product(
                brand = "Apple",
                title = "iPhone 15 pro",
                price = 799,
                discountPercentage = 10.5,
                rating = 4.0,
                thumbnail = "https://cdn.dummyjson.com/product-images/14/1.jpg"
            )
        )
        val loadCartDataState = LoadCartDataState(products,ApiState.Success)

        // Create test data for CartListUserState
        val userState = CartListUserState(
            loadCartDataState = ViewState(loadCartDataState))


        // Set up the content with the mocked userState
        composeTestRule.setContent {
            CartListContent(userState = userState)
        }

        // Verify if the UI elements are displayed correctly based on the test data
        composeTestRule
            .onNodeWithContentDescription("Samsung Z Fold")
            .assertExists()

        composeTestRule
            .onNodeWithText("₹100")
            .assertExists()

        composeTestRule
            .onNodeWithContentDescription("iPhone 15 pro")
            .assertExists()

        composeTestRule
            .onNodeWithText("₹799")
            .assertExists()

        // You can add more assertions as per your UI requirements
    }
}

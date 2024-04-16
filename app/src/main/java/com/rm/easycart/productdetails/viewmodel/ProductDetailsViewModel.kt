package com.rm.easycart.productdetails.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rm.easycart.core.model.Product
import com.rm.easycart.productdetails.state.AddToCartDataState
import com.rm.easycart.productdetails.state.ProductDetailsDataState
import com.rm.easycart.productdetails.usecase.AddToCartUseCase
import com.rm.easycart.productdetails.usecase.GetProductDetailsByIdUseCase
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.Resource
import com.rm.easycart.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val getProductDetailsById: GetProductDetailsByIdUseCase,
    private val addToCartUseCase: AddToCartUseCase,
) : ViewModel() {

    private val _productDataState = mutableStateOf(ViewState(ProductDetailsDataState()))
    val productDetailsDataState: State<ViewState<ProductDetailsDataState>> get() = _productDataState

    private val _addToCartDataState = mutableStateOf(ViewState(AddToCartDataState()))
    val addToCartDataState: State<ViewState<AddToCartDataState>> get() = _addToCartDataState

    /**
     * Get Product Details by ID is a function to fetch the Product details from the server
     * @param productId is a [Int] parameter that received from the Product List Screen
     */
    fun getProductDetailsByID(productId: Int?) = viewModelScope.launch {
        if (productId != null) getProductDetailsById.invoke(productId!!).collect { response ->
            when (response) {
                is Resource.Loading -> {
                    _productDataState.value = productDetailsDataState.value
                        .copy(ProductDetailsDataState().also { it.apiState = ApiState.Loading })
                }

                is Resource.Success -> {
                    val state = ProductDetailsDataState().apply {
                        apiState = ApiState.Success
                        message = response.message.toString()
                        product = response.data ?: Product()
                    }
                    _productDataState.value = productDetailsDataState.value.copy(value = state)
                }

                is Resource.Error -> {
                    val state = ProductDetailsDataState().apply {
                        apiState = ApiState.Error
                        message = response.message.toString()
                    }
                    _productDataState.value = productDetailsDataState.value.copy(value = state)
                }
            }
        }
    }

    /**
     * Add to Cart function to save a product to Database
     * @param product is type of [Product] which the user wish to add the the cart
     */
    fun addToCart(product: Product) = viewModelScope.launch {
        addToCartUseCase.invoke(product).collect { resource ->
            when (resource) {
                is Resource.Loading -> {
                    val state = AddToCartDataState(apiState = ApiState.Loading)
                    _addToCartDataState.value = addToCartDataState.value.copy(value = state)
                }

                is Resource.Success -> {
                    val state = AddToCartDataState(apiState = ApiState.Success)
                        .apply { id = resource.data ?: 0 }
                    _addToCartDataState.value = addToCartDataState.value.copy(value = state)
                }

                is Resource.Error -> {
                    val state = AddToCartDataState(apiState = ApiState.Error)
                    _addToCartDataState.value = addToCartDataState.value.copy(value = state)
                }
            }
        }
    }
}
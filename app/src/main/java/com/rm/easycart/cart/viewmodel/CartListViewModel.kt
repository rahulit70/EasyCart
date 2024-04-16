package com.rm.easycart.cart.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rm.easycart.cart.state.LoadCartDataState
import com.rm.easycart.cart.usecase.LoadCartUseCase
import com.rm.easycart.utils.ApiState
import com.rm.easycart.utils.Resource
import com.rm.easycart.utils.ViewState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartListViewModel @Inject constructor(
    private val loadCartUseCase: LoadCartUseCase
) : ViewModel() {
    private val _loadCartDataState = mutableStateOf(ViewState(LoadCartDataState()))
    val loadCartDataState: State<ViewState<LoadCartDataState>> get() = _loadCartDataState


    fun loadCart() = viewModelScope.launch(Dispatchers.IO) {
        loadCartUseCase.invoke().collect { response ->
            when (response) {
                is Resource.Loading -> {
                    val state = LoadCartDataState(apiState = ApiState.Loading)
                    _loadCartDataState.value = loadCartDataState.value.copy(value = state)
                }

                is Resource.Success -> {
                    val state = LoadCartDataState().apply {
                        apiState = ApiState.Success
                        products = response.data ?: listOf()
                    }
                    _loadCartDataState.value = loadCartDataState.value.copy(value = state)
                }

                is Resource.Error -> {
                    val state = LoadCartDataState(apiState = ApiState.Error)
                    _loadCartDataState.value = loadCartDataState.value.copy(value = state)
                }
            }
        }
    }

}
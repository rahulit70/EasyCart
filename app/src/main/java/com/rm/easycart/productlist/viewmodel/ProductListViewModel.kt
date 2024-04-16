package com.rm.easycart.productlist.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rm.easycart.core.model.Product
import com.rm.easycart.productlist.usecase.GetProductListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val getProductListUseCase: GetProductListUseCase,
) : ViewModel() {

    private val _productState: MutableStateFlow<PagingData<Product>> =
        MutableStateFlow(value = PagingData.empty())
    val productState: MutableStateFlow<PagingData<Product>> get() = _productState

    init {
        getProductsList()
    }

    /**
     * get list of product from the server
     * update the product list state and update the paginated data
     */
    fun getProductsList() = viewModelScope.launch {
        getProductListUseCase.invoke()
            .distinctUntilChanged()
            .cachedIn(viewModelScope)
            .collect { updateState(it) }
    }

    private fun updateState(it: PagingData<Product>) {
        _productState.value = it
    }
}
package com.rm.easycart.utils

import androidx.compose.runtime.Composable

sealed class ApiState {

    @Composable
    fun ResolveState(
        initial: @Composable () -> Unit = {},
        loading: @Composable () -> Unit = {},
        success: @Composable () -> Unit = {},
        error: @Composable () -> Unit = {},
    ) {
        when (this) {
            Initial -> initial.invoke()
            Loading -> loading.invoke()
            Success -> success.invoke()
            Error -> error.invoke()
        }
    }

    data object Initial : ApiState()
    data object Loading : ApiState()
    data object Success : ApiState()
    data object Error : ApiState()
    companion object {
        fun values(): Array<ApiState> {
            return arrayOf(Initial, Loading, Success, Error)
        }

        fun valueOf(value: String): ApiState {
            return when (value) {
                "Initial" -> Initial
                "Loading" -> Loading
                "Success" -> Success
                "Error" -> Error
                else -> throw IllegalArgumentException("No object com.rm.easycart.utils.ApiState.$value")
            }
        }
    }
}
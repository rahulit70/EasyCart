package com.rm.easycart.utils

import androidx.compose.material3.SnackbarDuration


typealias ShowSnackBar = (message: String, actionLabel: String, duration: SnackbarDuration) -> Unit
typealias onClick = () -> Unit
typealias onItemClick = (itemId: Int) -> Unit

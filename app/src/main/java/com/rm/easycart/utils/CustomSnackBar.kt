package com.rm.easycart.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.rm.easycart.R
import com.rm.easycart.ui.theme.Purple40
import com.rm.easycart.ui.theme.PurpleGrey40
import com.rm.easycart.utils.AppConstants.Companion.API_FAILED
import com.rm.easycart.utils.AppConstants.Companion.API_SUCCESS
import com.rm.easycart.utils.AppConstants.Companion.INFO

@Composable
fun CustomSnackBar(snackBarHostState: SnackbarHostState) {
    SnackbarHost(hostState = snackBarHostState, snackbar = {
        Snackbar(
            action = {
                IconButton(onClick = { it.dismiss() }) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(id = R.string.close_button),
                        tint = if (isSystemInDarkTheme()) White else Black
                    )
                }
            },
            shape = RectangleShape,
            containerColor = if (isSystemInDarkTheme()) Black else LightGray
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                DynamicIcon(snackBarData = it)
                Text(
                    text = it.visuals.message,
                    color = if (isSystemInDarkTheme()) White else Black,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    })
}

@Composable
private fun DynamicIcon(snackBarData: SnackbarData) {
    when (snackBarData.visuals.actionLabel) {
        API_SUCCESS -> SuccessIcon()
        API_FAILED -> FailIcon()
        INFO -> InfoIcon()
    }
}

@Composable
fun InfoIcon() {
    Box(modifier = Modifier.padding(10.dp)) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Purple40 else PurpleGrey40,
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun SuccessIcon() {
    Box(modifier = Modifier.padding(10.dp)) {
        Icon(
            imageVector = Icons.Default.Done,
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Purple40 else PurpleGrey40,
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
private fun FailIcon() {
    Box(modifier = Modifier.padding(10.dp)) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            tint = if (isSystemInDarkTheme()) Purple40 else PurpleGrey40,
            modifier = Modifier
                .padding(5.dp)
                .align(Alignment.Center)
        )
    }
}

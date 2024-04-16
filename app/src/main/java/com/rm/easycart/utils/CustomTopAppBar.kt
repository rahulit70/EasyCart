package com.rm.easycart.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.rm.easycart.R
import com.rm.easycart.ui.theme.Purple40

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTopAppBar(
    scrollBehavior: TopAppBarScrollBehavior,
    title: String = AppConstants.EMPTY_STRING,
    navigationIcon: ImageVector? = null,
    actionIcon: ImageVector? = null,
    navOnClick: onClick = {},
    actionOnClick: onClick = {}
) {
    Column {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(if (isSystemInDarkTheme()) Color.Black else Purple40),
            title = {
                Text(
                    text = title,
                    maxLines = 1,
                    color = White
                )
            },
            navigationIcon = {
                if (navigationIcon != null) IconButton(onClick = { navOnClick.invoke() }) {
                    Icon(
                        imageVector = navigationIcon,
                        contentDescription = stringResource(id = R.string.nav_button),
                        tint = White
                    )
                }
            },
            actions = {
                if (actionIcon != null) IconButton(onClick = { actionOnClick.invoke() }) {
                    Icon(
                        imageVector = actionIcon,
                        contentDescription = stringResource(id = R.string.action_button),
                        tint = White
                    )
                }
            },
            scrollBehavior = scrollBehavior,
        )
        if (isSystemInDarkTheme()) {
            Divider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Gray
            )
        }
    }
}
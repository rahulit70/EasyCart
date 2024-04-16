package com.rm.easycart

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.rm.easycart.core.state.rememberAppState
import com.rm.easycart.navigation.ScreenNavigation
import com.rm.easycart.ui.theme.EasyCartTheme
import com.rm.easycart.utils.CustomSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EasyCartTheme {
                val appState = rememberAppState()

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        snackbarHost = { CustomSnackBar(snackBarHostState = appState.snackBarHostState) }
                    ) { padding ->
                        Column(modifier = Modifier.padding(padding)) {
                            ScreenNavigation(
                                navHostController = appState.navController,
                                showSnackBar = { message, actionLabel, duration ->
                                    appState.showSnackBar(
                                        message,
                                        actionLabel,
                                        duration
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}


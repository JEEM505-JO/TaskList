package com.devjeem.tasklist.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aes.medidoraes.ui.splash.SplashScreen
import com.aes.medidoraes.ui.splash.SplashViewModel
import com.devjeem.tasklist.ui.listTask.ListTaskScreen
import com.devjeem.tasklist.ui.listTask.ListTaskVM


@Composable
fun MainNavHost() {
    val navHostController = rememberNavController()
    val startDestination = AppScreens.Splash.route


    if (startDestination.isNotEmpty()) {
        NavHost(navController = navHostController, startDestination = startDestination) {
            composable(AppScreens.Splash.route){
                val splashViewModel: SplashViewModel = hiltViewModel()
                val state by splashViewModel.uiState.collectAsState()
                SplashScreen(state = state)
                LaunchedEffect(key1 = Unit, block = {
                    state.setListTaskNav {
                        navHostController.navigate(AppScreens.ListTask.route) {
                            popUpTo(AppScreens.Splash.route) {
                                inclusive = true
                            }
                        }
                    }
                })
            }
            composable(AppScreens.ListTask.route){
                ContentNavButton()
            }
        }
    }
}
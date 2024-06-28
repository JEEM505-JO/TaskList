package com.devjeem.tasklist.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.devjeem.tasklist.R
import com.devjeem.tasklist.ui.addTask.AddTaskScreen
import com.devjeem.tasklist.ui.addTask.AddTaskVM
import com.devjeem.tasklist.ui.listTask.ListTaskScreen
import com.devjeem.tasklist.ui.listTask.ListTaskVM
import com.devjeem.tasklist.ui.order.OrderScreen


@Composable
fun ContentNavButton() {
    val navHost = rememberNavController()
    val bottomBarState = rememberSaveable { (mutableStateOf(true)) }
    val navBackStackEntry by navHost.currentBackStackEntryAsState()

    //Validar si se debe mostrar el botton navigation bar
    when (navBackStackEntry?.destination?.route) {
        "${AppScreens.AddEditTask.route}/{ID}" -> {
            bottomBarState.value = false
        }

        else -> {
            bottomBarState.value = true
        }
    }

    Scaffold(bottomBar = {
        AnimatedVisibility(
            visible = bottomBarState.value,
            enter = slideInVertically(initialOffsetY = { it }),
            exit = slideOutVertically(targetOffsetY = { it }),
            content = {
                AppBottomBar(navHost = navHost)
            }
        )
    }) { paddingValues ->
        NavHost(
            navController = navHost,
            startDestination = AppScreens.ListTask.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(
                route = AppScreens.ListTask.route
            ) {
                val viewModel: ListTaskVM = hiltViewModel()
                val state by viewModel.state.collectAsState()
                LaunchedEffect(key1 = Unit) {
                    state.setAddTaskNav {
                        navHost.navigate("${AppScreens.AddEditTask.route}/$it")
                    }
                }
                ListTaskScreen(stateTask = state)
            }
            composable(route = "${AppScreens.AddEditTask.route}/{ID}",
                enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(1000)
                )
            }, exitTransition = {
                slideOutOfContainer(
                    AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(1000)
                )
            }) {
                val id = it.arguments?.getString("ID")
                val viewModel: AddTaskVM = hiltViewModel()
                val state by viewModel.state.collectAsState()
                LaunchedEffect(key1 = Unit) {
                    if (id != null) {
                        state.getDataFromId(id.toInt())
                    } else {
                        state.getDataFromId(0)
                    }
                    state.setTaskListNav {
                        navHost.navigate(AppScreens.ListTask.route) {
                            popUpTo(AppScreens.AddEditTask.route) {
                                inclusive = true
                            }
                        }
                    }
                }
                AddTaskScreen(stateAddTask = state)
            }
            composable(route = AppScreens.Order.route) {
                OrderScreen()
            }
        }
    }
}


@Composable
fun AppBottomBar(navHost: NavHostController) {
    NavigationBar(
        modifier = Modifier
            .border(
                BorderStroke(1.dp, color = Color.Black),
                shape = RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)
            )
            .clip(RoundedCornerShape(topEnd = 30.dp, topStart = 30.dp)),
        containerColor = colorResource(id = R.color.white),
    ) {
        val bottomNavigationItems =
            listOf(AppScreens.ListTask, AppScreens.Order)
        val navBackStackEntry by navHost.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination?.route ?: AppScreens.ListTask

        bottomNavigationItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentDestination == navItem.route,
                onClick = {
                    navHost.navigate(navItem.route) {
                        popUpTo(navHost.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = navItem.icon,
                        contentDescription = stringResource(id = navItem.title),
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = navItem.title),
                    )
                },
                colors = NavigationBarItemColors(
                    selectedIconColor = colorResource(id = R.color.iconColor),
                    disabledIconColor = colorResource(id = R.color.gray1),
                    disabledTextColor = colorResource(id = R.color.gray),
                    selectedTextColor = colorResource(id = R.color.black),
                    selectedIndicatorColor = colorResource(id = R.color.gray1),
                    unselectedTextColor = colorResource(id = R.color.gray),
                    unselectedIconColor = colorResource(id = R.color.black)
                )
            )

        }
    }
}


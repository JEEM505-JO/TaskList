package com.devjeem.tasklist.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Favorite

import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Refresh
import androidx.compose.ui.graphics.vector.ImageVector
import com.devjeem.tasklist.R

sealed class AppScreens : GenericScreens {

    object Splash : AppScreens() {
        override val route: String
            get() = "splash"
    }


    object ListTask : AppScreens() {
        override val route: String
            get() = "listTask"
        override val title: Int
            get() = R.string.lisTask
        override val icon: ImageVector
            get() = Icons.Outlined.Edit
    }

    object AddEditTask : AppScreens() {
        override val route: String
            get() = "addEditTask"
        override val title: Int
            get() = R.string.AddTask
    }

    object Order : AppScreens(){
        override val route: String
            get() = "home"
        override val title: Int
            get() = R.string.Order
        override val icon: ImageVector
            get() = Icons.Outlined.Refresh
    }

    object ListImage : AppScreens(){
        override val route: String
            get() = "listImage"
        override val title: Int
            get() = R.string.ListImage
        override val icon: ImageVector
            get() = Icons.Outlined.Favorite
    }

    object DetailOrder : AppScreens() {
        override val route: String
            get() = "detailOrder"
    }












}
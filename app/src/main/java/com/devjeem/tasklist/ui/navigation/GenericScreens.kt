package com.devjeem.tasklist.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.ui.graphics.vector.ImageVector

interface GenericScreens {
    val route: String
    val title: Int
        @StringRes get() = -1
    val icon: ImageVector
        get() = Icons.Filled.Favorite
    val stringParam: String?
        get() = null

}
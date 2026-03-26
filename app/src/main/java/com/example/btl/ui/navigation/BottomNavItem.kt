package com.example.btl.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material.icons.outlined.BarChart
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.Widgets
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * File: ui/navigation/BottomNavItem.kt
 *
 * Sealed class định nghĩa các tab trong BottomNavigation.
 */
sealed class BottomNavItem(
    val route: String,
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
) {
    data object Home : BottomNavItem(
        route = Routes.HOME,
        title = "Trang chủ",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home
    )

    data object Utilities : BottomNavItem(
        route = Routes.UTILITIES,
        title = "Tiện ích",
        selectedIcon = Icons.Filled.Widgets,
        unselectedIcon = Icons.Outlined.Widgets
    )

    data object Calendar : BottomNavItem(
        route = Routes.CALENDAR,
        title = "Lịch",
        selectedIcon = Icons.Filled.CalendarMonth,
        unselectedIcon = Icons.Outlined.CalendarMonth
    )

    data object Settings : BottomNavItem(
        route = Routes.SETTINGS,
        title = "Cài đặt",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings
    )

    companion object {
        val items = listOf(Home, Utilities, Calendar, Settings)
    }
}
package com.example.btl.ui.screen.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.btl.ui.navigation.BottomNavItem
import com.example.btl.ui.navigation.Routes
import com.example.btl.ui.screen.home.HomeScreen

/**
 * File: ui/screen/main/MainScreen.kt
 *
 * Màn hình chính chứa BottomNavigationBar.
 * BottomNav chỉ hiện khi đang ở các tab chính (Home, Calendar, Statistics, Settings).
 * Khi navigate vào màn con (AddTask, TaskDetail...) thì BottomNav sẽ ẩn đi
 * vì các màn con được navigate từ AppNavigation (navController cấp trên).
 */

private val PrimaryBlue = Color(0xFF2563EB)

@Composable
fun MainScreen(
    onNavigateToAddTask: () -> Unit = {},
    onNavigateToTaskDetail: (String) -> Unit = {}
) {
    // NavController riêng cho các tab bên trong BottomNav
    val bottomNavController = rememberNavController()
    val navBackStackEntry by bottomNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White,
                tonalElevation = 0.dp
            ) {
                BottomNavItem.items.forEach { item ->
                    val selected = currentDestination?.hierarchy?.any {
                        it.route == item.route
                    } == true

                    NavigationBarItem(
                        selected = selected,
                        onClick = {
                            bottomNavController.navigate(item.route) {
                                // Tránh tạo nhiều instance của cùng 1 destination
                                popUpTo(bottomNavController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Icon(
                                imageVector = if (selected) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        label = {
                            Text(
                                text = item.title,
                                fontSize = 11.sp,
                                fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = PrimaryBlue,
                            selectedTextColor = PrimaryBlue,
                            unselectedIconColor = Color(0xFF94A3B8),
                            unselectedTextColor = Color(0xFF94A3B8),
                            indicatorColor = Color(0xFFEFF6FF)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        // NavHost cho các tab — BottomNav luôn hiện ở đây
        NavHost(
            navController = bottomNavController,
            startDestination = Routes.HOME,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Routes.HOME) {
                HomeScreen(
                    onNavigateToAddTask = onNavigateToAddTask,
                    onNavigateToTaskDetail = onNavigateToTaskDetail
                )
            }
            composable(Routes.UTILITIES) {
                // TODO: UtilitiesScreen() — sẽ chứa Thống kê bên trong
                PlaceholderScreen(title = "Tiện ích")
            }
            composable(Routes.CALENDAR) {
                PlaceholderScreen(title = "Lịch")
            }
            composable(Routes.SETTINGS) {
                // TODO: SettingsScreen()
                PlaceholderScreen(title = "Cài đặt")
            }
        }
    }
}

/**
 * Màn hình tạm cho các tab chưa xây dựng.
 */
@Composable
fun PlaceholderScreen(title: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Text(
            text = title,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1E293B)
        )
    }
}
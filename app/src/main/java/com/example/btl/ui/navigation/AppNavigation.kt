package com.example.btl.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.btl.ui.screen.main.MainScreen
import com.example.btl.ui.screen.splash.SplashScreen

/**
 * File: ui/navigation/AppNavigation.kt
 *
 * NavHost cấp cao nhất (root) của toàn app.
 *
 * Luồng điều hướng:
 *   Splash → Main (chứa BottomNav với Home, Calendar, Statistics, Settings)
 *                ↘ AddTask      (không có BottomNav)
 *                ↘ TaskDetail   (không có BottomNav)
 *
 * Lý do tách 2 tầng NavHost:
 * - Tầng 1 (AppNavigation): Splash → Main → các màn con (AddTask, TaskDetail...)
 * - Tầng 2 (trong MainScreen): Home, Calendar, Statistics, Settings
 * → Khi ở tầng 2, BottomNav luôn hiện.
 * → Khi navigate sang tầng 1 (AddTask, TaskDetail), BottomNav tự động ẩn.
 */
@Composable
fun AppNavigation() {
    val rootNavController = rememberNavController()

    NavHost(
        navController = rootNavController,
        startDestination = Routes.SPLASH
    ) {
        // Splash
        composable(Routes.SPLASH) {
            SplashScreen(
                onSplashFinished = {
                    rootNavController.navigate(Routes.MAIN) {
                        popUpTo(Routes.SPLASH) { inclusive = true }
                    }
                }
            )
        }

        // Main (chứa BottomNav)
        composable(Routes.MAIN) {
            MainScreen(
                onNavigateToAddTask = {
                    rootNavController.navigate(Routes.ADD_TASK)
                },
                onNavigateToTaskDetail = { taskId ->
                    rootNavController.navigate(Routes.taskDetail(taskId))
                }
            )
        }

        //  Các màn con — KHÔNG có BottomNav
        composable(Routes.ADD_TASK) {
            // TODO: AddTaskScreen()
        }

        composable(Routes.TASK_DETAIL) {
            // val taskId = it.arguments?.getString("taskId")
            // TODO: TaskDetailScreen(taskId)
        }
    }
}
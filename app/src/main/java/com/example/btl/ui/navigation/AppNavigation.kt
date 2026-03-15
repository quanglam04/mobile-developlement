package com.example.btl.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.btl.ui.screen.home.HomeScreen
import com.example.btl.ui.screen.splash.SplashScreen

/**
 * NavHost chính của app.
 * Khi có màn hình mới:
 *   1. Thêm route vào Routes.kt
 *   2. Thêm composable(...) ở đây
 */
@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.Splash.route
    ) {
        // Màn Splash
        composable(Routes.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigate(Routes.Home.route) {
                        // Xóa Splash khỏi back stack để bấm Back không quay lại Splash
                        popUpTo(Routes.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        // Màn Home
        composable(Routes.Home.route) {
            HomeScreen()
        }

        //  Thêm màn hình mới ở đây
        // composable(Routes.Login.route) {
        //     LoginScreen(navController)
        // }
    }
}
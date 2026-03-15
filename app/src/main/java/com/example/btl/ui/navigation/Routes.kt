package com.example.btl.ui.navigation

/**
 * Khai báo tất cả route ở đây.
 * Khi có màn hình mới, thêm 1 dòng object vào là xong.
 */
sealed class Routes(val route: String) {
    object Splash : Routes("splash")
    object Home : Routes("home")

    // Thêm route mới ở đây
    // object Login : Routes("login")
    // object Profile : Routes("profile")
    // object Settings : Routes("settings")
}
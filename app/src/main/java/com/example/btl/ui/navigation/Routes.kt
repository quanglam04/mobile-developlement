package com.example.btl.ui.navigation

/**
 * File: ui/navigation/Routes.kt
 *
 * Khai báo tên tất cả route trong app.
 * Khi thêm màn hình mới, chỉ cần thêm const ở đây.
 */
object Routes {
    // Splash
    const val SPLASH = "splash"

    // Main (chứa BottomNavigation)
    const val MAIN = "main"

    // Các tab trong BottomNavigation
    const val HOME = "home"
    const val CALENDAR = "calendar"
    const val STATISTICS = "statistics"
    const val UTILITIES = "utilities"
    const val SETTINGS = "settings"

    // Các màn hình con (không hiện BottomNav)
    const val ADD_TASK = "add_task"
    const val TASK_DETAIL = "task_detail/{taskId}"

    fun taskDetail(taskId: String) = "task_detail/$taskId"
}
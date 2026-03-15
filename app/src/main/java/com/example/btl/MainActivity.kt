package com.example.btl

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.b2.ui.theme.B2Theme
import com.example.btl.ui.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            B2Theme() {
                val navController = rememberNavController()
                AppNavigation(navController = navController)
            }
        }
    }
}
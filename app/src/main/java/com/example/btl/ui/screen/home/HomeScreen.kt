package com.example.btl.ui.screen.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.btl.BuildConfig

@Composable
fun HomeScreen() {
    val baseUrl = BuildConfig.BASE_URL
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Home Screen",
            style = MaterialTheme.typography.headlineLarge
        )

        Text(
            text = "Chào mừng bạn đến trang chủ! ${baseUrl}",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
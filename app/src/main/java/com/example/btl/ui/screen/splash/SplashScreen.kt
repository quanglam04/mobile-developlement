package com.example.btl.ui.screen.splash

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

/**
 * File: ui/screen/splash/SplashScreen.kt
 *
 * Màn hình Splash hiển thị khi mở app.
 * Sau 3 giây sẽ tự động chuyển sang màn Main.
 */

// Màu xanh chủ đạo của app
private val PrimaryBlue = Color(0xFF2563EB)
private val DarkText = Color(0xFF1E293B)
private val GrayText = Color(0xFF94A3B8)
private val BackgroundWhite = Color(0xFFF8FAFC)

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    // Animation cho progress bar
    val progress = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        // Animate progress từ 0 -> 1 trong 2.5 giây
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2500,
                easing = LinearEasing
            )
        )
        // Delay thêm chút trước khi chuyển màn
        delay(300)
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundWhite),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(20.dp))
                    .background(PrimaryBlue),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.CheckCircle,
                    contentDescription = "FocusFlow Logo",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "FocusFlow",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif,
                color = DarkText
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "TỐI ƯU HÓA NĂNG SUẤT CÁ NHÂN",
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = GrayText,
                letterSpacing = 2.sp,
                textAlign = TextAlign.Center
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 80.dp)
                .fillMaxWidth()
                .padding(horizontal = 60.dp)
        ) {
            LinearProgressIndicator(
                progress = { progress.value },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp)),
                color = PrimaryBlue,
                trackColor = Color(0xFFE2E8F0),
                strokeCap = StrokeCap.Round,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "INITIALIZING WORKSPACE",
                fontSize = 11.sp,
                fontWeight = FontWeight.Normal,
                color = GrayText,
                letterSpacing = 2.sp
            )
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(onSplashFinished = {})
}
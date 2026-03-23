package com.example.pathway01.ui.theme

import android.app.Activity
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val ColorScheme = darkColorScheme(
    primary = Orange,
    onPrimary = White,
    primaryContainer = OrangeDim,
    onPrimaryContainer = OrangeLight,

    secondary = OrangeLight,
    onSecondary = White,
    secondaryContainer = CardGray,
    onSecondaryContainer = White,

    background = Black,
    onBackground = White,

    surface = DarkGray,
    onSurface = White,
    onSurfaceVariant = TextSecondary,

    outline = SurfaceGray
)

@Composable
fun Pathway01Theme(content: @Composable () -> Unit) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = Black.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }
    MaterialTheme(
        colorScheme = ColorScheme,
        typography = Typography,
        content = content
    )
}
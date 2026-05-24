package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val EditorialColorScheme = darkColorScheme(
    primary = LightMutedGreen,
    onPrimary = LightText,
    secondary = SoftGold,
    onSecondary = DarkText,
    background = DeepCharcoal,
    onBackground = LightText,
    surface = SurfaceCharcoal,
    onSurface = LightText,
    surfaceVariant = WarmIvory,
    onSurfaceVariant = DarkText,
    outline = CardBorder,
    error = WarningRed
)

@Composable
fun BeforeYouBuyTheme(
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = EditorialColorScheme,
        typography = Typography,
        content = content
    )
}

// Keep MyApplicationTheme for backward compatibility with existing tests
@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    BeforeYouBuyTheme(content = content)
}

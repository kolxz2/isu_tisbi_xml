package ru.nikolas_snek.composelistview.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.ViewCompat

private val DarkColorScheme = darkColorScheme(
    surface = Purple40,
    onSurface = Color.White,
    primary = LightBlue,
    onPrimary = Color.White,
    background = Navy,
    secondary = NavyLight,
    onSecondary = PurpleGrey40
)

private val LightColorScheme = lightColorScheme(
    surface = Blue,
    onSurface = BlueLight,
    primary = Blue,
    onPrimary = Color.White,
    background = Color.White,
    secondary = LightBlue,
    onSecondary = Color.White
)

@Composable
fun BasicsCodelabTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    //  dynamicColor: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = when {
        /* dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
             val context = LocalContext.current
             if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
         }*/
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            (view.context as Activity).window.statusBarColor = colorScheme.background.toArgb()
            ViewCompat.getWindowInsetsController(view)?.isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
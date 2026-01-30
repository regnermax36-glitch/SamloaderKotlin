package tk.zwander.commonCompose.view.components

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import tk.zwander.common.util.BifrostSettings
import tk.zwander.commonCompose.view.LocalUseTransparencyEffects

/**
 * OneUI 8.5 Theme for Android 16
 * Replaces the original BifrostTheme with Samsung OneUI design principles
 */
@Composable
fun OneUITheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val useTransparency by BifrostSettings.useTransparencyEffects.collectAsState(false)
    
    val colorScheme = if (darkTheme) {
        OneUIDarkColorScheme
    } else {
        OneUILightColorScheme
    }
    
    MaterialTheme(
        colorScheme = colorScheme,
        typography = OneUITypography,
        content = {
            CompositionLocalProvider(
                LocalUseTransparencyEffects provides useTransparency,
            ) {
                content()
            }
        }
    )
}

/**
 * Legacy compatibility wrapper
 * Maintains compatibility with existing BifrostTheme usage
 */
@Composable
fun BifrostTheme(content: @Composable () -> Unit) {
    OneUITheme(content = content)
}

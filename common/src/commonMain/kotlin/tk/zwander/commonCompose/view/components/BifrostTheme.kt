package tk.zwander.commonCompose.view.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import tk.zwander.common.util.BifrostSettings
import tk.zwander.commonCompose.view.LocalUseTransparencyEffects

/**
 * Updated BifrostTheme using OneUI 8.5 design system
 * Maintains backward compatibility while providing modern OneUI styling
 */
@Composable
fun BifrostTheme(block: @Composable () -> Unit) {
    // Use the new OneUI theme instead of DynamicMaterialTheme
    OneUITheme {
        block()
    }
}

package tk.zwander.commonCompose.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import tk.zwander.commonCompose.view.LocalUseTransparencyEffects

/**
 * OneUI 8.5 styled card component
 * Follows Samsung's OneUI design principles with proper spacing, elevation, and corner radius
 */
@Composable
fun OneUICard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = OneUIDesignTokens.Elevation.OneUICard,
    content: @Composable ColumnScope.() -> Unit
) {
    val useTransparency = LocalUseTransparencyEffects.current
    
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = OneUIDesignTokens.CornerRadius.OneUICard,
        colors = CardDefaults.cardColors(
            containerColor = if (useTransparency) Color.Transparent else backgroundColor,
            contentColor = contentColor
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = elevation)
    ) {
        Column(
            modifier = Modifier.padding(OneUIDesignTokens.Spacing.OneUICardPadding)
        ) {
            content()
        }
    }
}

/**
 * OneUI 8.5 styled surface card for more complex layouts
 */
@Composable
fun OneUISurface(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    elevation: Dp = OneUIDesignTokens.Elevation.OneUICard,
    content: @Composable () -> Unit
) {
    val useTransparency = LocalUseTransparencyEffects.current
    
    Surface(
        modifier = modifier,
        shape = OneUIDesignTokens.CornerRadius.OneUICard,
        color = if (useTransparency) Color.Transparent else backgroundColor,
        contentColor = contentColor,
        tonalElevation = elevation,
        shadowElevation = elevation
    ) {
        Box(
            modifier = Modifier.padding(OneUIDesignTokens.Spacing.OneUICardPadding)
        ) {
            content()
        }
    }
}

/**
 * OneUI 8.5 styled elevated card for important content
 */
@Composable
fun OneUIElevatedCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
    content: @Composable ColumnScope.() -> Unit
) {
    OneUICard(
        modifier = modifier,
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        elevation = OneUIDesignTokens.Elevation.Level3,
        content = content
    )
}

/**
 * OneUI 8.5 styled outline card for secondary content
 */
@Composable
fun OneUIOutlineCard(
    modifier: Modifier = Modifier,
    backgroundColor: Color = Color.Transparent,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
    content: @Composable ColumnScope.() -> Unit
) {
    val useTransparency = LocalUseTransparencyEffects.current
    
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = OneUIDesignTokens.CornerRadius.OneUICard,
        color = if (useTransparency) Color.Transparent else backgroundColor,
        contentColor = contentColor,
        border = androidx.compose.foundation.BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier = Modifier.padding(OneUIDesignTokens.Spacing.OneUICardPadding)
        ) {
            content()
        }
    }
}

package tk.zwander.commonCompose.view.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

/**
 * OneUI 8.5 Primary Button
 * Main action button with Samsung OneUI styling
 */
@Composable
fun OneUIPrimaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = OneUIDesignTokens.CornerRadius.OneUIButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(
            horizontal = OneUIDesignTokens.Spacing.OneUIButtonPadding,
            vertical = OneUIDesignTokens.Spacing.Medium
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = OneUIDesignTokens.Elevation.OneUIButton
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * OneUI 8.5 Secondary Button
 * Secondary action button with tonal styling
 */
@Composable
fun OneUISecondaryButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer
) {
    FilledTonalButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = OneUIDesignTokens.CornerRadius.OneUIButton,
        colors = ButtonDefaults.filledTonalButtonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(
            horizontal = OneUIDesignTokens.Spacing.OneUIButtonPadding,
            vertical = OneUIDesignTokens.Spacing.Medium
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * OneUI 8.5 Outline Button
 * Tertiary action button with outline styling
 */
@Composable
fun OneUIOutlineButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    borderColor: Color = MaterialTheme.colorScheme.outline,
    contentColor: Color = MaterialTheme.colorScheme.primary
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier.height(48.dp),
        enabled = enabled,
        shape = OneUIDesignTokens.CornerRadius.OneUIButton,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        border = ButtonDefaults.outlinedButtonBorder.copy(
            brush = androidx.compose.foundation.BorderStroke(
                width = 1.dp,
                color = if (enabled) borderColor else MaterialTheme.colorScheme.surfaceVariant
            ).brush
        ),
        contentPadding = PaddingValues(
            horizontal = OneUIDesignTokens.Spacing.OneUIButtonPadding,
            vertical = OneUIDesignTokens.Spacing.Medium
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * OneUI 8.5 Text Button
 * Minimal action button for less important actions
 */
@Composable
fun OneUITextButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    contentColor: Color = MaterialTheme.colorScheme.primary
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.height(40.dp),
        enabled = enabled,
        colors = ButtonDefaults.textButtonColors(
            contentColor = contentColor,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(
            horizontal = OneUIDesignTokens.Spacing.Medium,
            vertical = OneUIDesignTokens.Spacing.Small
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge.copy(
                fontWeight = FontWeight.Medium
            )
        )
    }
}

/**
 * OneUI 8.5 Large Button
 * Prominent action button for primary CTAs
 */
@Composable
fun OneUILargeButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    text: String,
    backgroundColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary
) {
    Button(
        onClick = onClick,
        modifier = modifier.height(56.dp),
        enabled = enabled,
        shape = OneUIDesignTokens.CornerRadius.OneUIButton,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = contentColor,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        contentPadding = PaddingValues(
            horizontal = OneUIDesignTokens.Spacing.Large,
            vertical = OneUIDesignTokens.Spacing.Medium
        ),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = OneUIDesignTokens.Elevation.Level2
        )
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
    }
}

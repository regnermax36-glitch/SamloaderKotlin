package tk.zwander.commonCompose.view.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.icerock.moko.resources.compose.painterResource
import dev.icerock.moko.resources.compose.stringResource
import tk.zwander.commonCompose.view.LocalUseTransparencyEffects

/**
 * OneUI 8.5 Bottom Navigation Bar
 * Replaces the traditional tab row with Samsung OneUI navigation patterns
 */
@Composable
fun OneUIBottomNavigation(
    selectedPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val useTransparency = LocalUseTransparencyEffects.current

    Surface(
        modifier = modifier.fillMaxWidth(),
        color = if (useTransparency) Color.Transparent else MaterialTheme.colorScheme.surface,
        tonalElevation = OneUIDesignTokens.Elevation.Level2,
        shadowElevation = OneUIDesignTokens.Elevation.Level2
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = OneUIDesignTokens.Spacing.Medium,
                    vertical = OneUIDesignTokens.Spacing.Small
                ),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            pages.forEachIndexed { index, page ->
                OneUINavigationItem(
                    page = page,
                    isSelected = selectedPage == index,
                    onClick = { onPageSelected(index) },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * Individual navigation item with OneUI 8.5 styling
 */
@Composable
private fun OneUINavigationItem(
    page: Page,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val interactionSource = remember { MutableInteractionSource() }

    // Animation values
    val iconScale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = "iconScale"
    )

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(durationMillis = 200),
        label = "iconColor"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(durationMillis = 200),
        label = "textColor"
    )

    val textAlpha by animateFloatAsState(
        targetValue = if (isSelected) 1f else 0.7f,
        animationSpec = tween(durationMillis = 200),
        label = "textAlpha"
    )

    Column(
        modifier = modifier
            .clip(OneUIDesignTokens.CornerRadius.Medium)
            .clickable(
                interactionSource = interactionSource,
                indication = androidx.compose.material3.ripple(
                    bounded = true,
                    radius = 32.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) { onClick() }
            .padding(
                horizontal = OneUIDesignTokens.Spacing.Small,
                vertical = OneUIDesignTokens.Spacing.Medium
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon with selection indicator
        Box(
            contentAlignment = Alignment.Center
        ) {
            // Selection background
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            shape = CircleShape
                        )
                )
            }

            Icon(
                painter = painterResource(page.iconRes),
                contentDescription = stringResource(page.labelRes),
                modifier = Modifier
                    .size(24.dp)
                    .scale(iconScale),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Label
        Text(
            text = stringResource(page.labelRes),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Medium,
                fontSize = 11.sp
            ),
            color = textColor,
            modifier = Modifier.alpha(textAlpha),
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

/**
 * OneUI 8.5 Rail Navigation for larger screens
 * Alternative navigation layout for tablets and desktop
 */
@Composable
fun OneUINavigationRail(
    selectedPage: Int,
    onPageSelected: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val useTransparency = LocalUseTransparencyEffects.current

    Surface(
        modifier = modifier.width(80.dp),
        color = if (useTransparency) Color.Transparent else MaterialTheme.colorScheme.surface,
        tonalElevation = OneUIDesignTokens.Elevation.Level1
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = OneUIDesignTokens.Spacing.Medium),
            verticalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Small),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            pages.forEachIndexed { index, page ->
                OneUIRailNavigationItem(
                    page = page,
                    isSelected = selectedPage == index,
                    onClick = { onPageSelected(index) }
                )
            }
        }
    }
}

/**
 * Individual rail navigation item
 */
@Composable
private fun OneUIRailNavigationItem(
    page: Page,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }

    val iconColor by animateColorAsState(
        targetValue = if (isSelected) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(durationMillis = 200),
        label = "railIconColor"
    )

    Column(
        modifier = Modifier
            .clip(OneUIDesignTokens.CornerRadius.Medium)
            .clickable(
                interactionSource = interactionSource,
                indication = androidx.compose.material3.ripple(
                    bounded = true,
                    radius = 28.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            ) { onClick() }
            .padding(OneUIDesignTokens.Spacing.Medium),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (isSelected) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.3f),
                            shape = OneUIDesignTokens.CornerRadius.Medium
                        )
                )
            }

            Icon(
                painter = painterResource(page.iconRes),
                contentDescription = stringResource(page.labelRes),
                modifier = Modifier.size(24.dp),
                tint = iconColor
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = stringResource(page.labelRes),
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                fontSize = 10.sp
            ),
            color = iconColor,
            textAlign = TextAlign.Center,
            maxLines = 1
        )
    }
}

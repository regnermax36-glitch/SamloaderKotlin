package tk.zwander.commonCompose.view.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * OneUI 8.5 Design Tokens for Android 16
 * Based on Samsung's OneUI design system
 */
object OneUIDesignTokens {
    
    // OneUI 8.5 Color Palette
    object Colors {
        // Primary Colors (Samsung Blue)
        val Primary = Color(0xFF1976D2)
        val PrimaryVariant = Color(0xFF0D47A1)
        val OnPrimary = Color(0xFFFFFFFF)
        
        // Secondary Colors (Samsung Green)
        val Secondary = Color(0xFF00C853)
        val SecondaryVariant = Color(0xFF00A040)
        val OnSecondary = Color(0xFF000000)
        
        // Surface Colors
        val Surface = Color(0xFFFAFAFA)
        val SurfaceVariant = Color(0xFFF5F5F5)
        val OnSurface = Color(0xFF1C1C1C)
        val OnSurfaceVariant = Color(0xFF666666)
        
        // Background Colors
        val Background = Color(0xFFFFFFFF)
        val OnBackground = Color(0xFF1C1C1C)
        
        // Error Colors
        val Error = Color(0xFFD32F2F)
        val OnError = Color(0xFFFFFFFF)
        
        // OneUI Specific Colors
        val OneUIBlue = Color(0xFF1976D2)
        val OneUIGreen = Color(0xFF00C853)
        val OneUIGray = Color(0xFF9E9E9E)
        val OneUILightGray = Color(0xFFF5F5F5)
        val OneUIDarkGray = Color(0xFF424242)
        
        // Dark Theme Colors
        val DarkSurface = Color(0xFF121212)
        val DarkSurfaceVariant = Color(0xFF1E1E1E)
        val DarkBackground = Color(0xFF000000)
        val DarkOnSurface = Color(0xFFE0E0E0)
        val DarkOnBackground = Color(0xFFFFFFFF)
    }
    
    // OneUI 8.5 Typography System
    object Typography {
        val DisplayLarge = TextStyle(
            fontFamily = FontFamily.Default, // Samsung One font would be ideal
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = (-0.25).sp
        )
        
        val DisplayMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        )
        
        val DisplaySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        )
        
        val HeadlineLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        )
        
        val HeadlineMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        )
        
        val HeadlineSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        )
        
        val TitleLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        )
        
        val TitleMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        )
        
        val TitleSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        )
        
        val BodyLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        )
        
        val BodyMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        )
        
        val BodySmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        )
        
        val LabelLarge = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        )
        
        val LabelMedium = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
        
        val LabelSmall = TextStyle(
            fontFamily = FontFamily.Default,
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    }
    
    // OneUI 8.5 Spacing System
    object Spacing {
        val ExtraSmall = 4.dp
        val Small = 8.dp
        val Medium = 16.dp
        val Large = 24.dp
        val ExtraLarge = 32.dp
        val XXLarge = 48.dp
        val XXXLarge = 64.dp
        
        // OneUI specific spacing
        val OneUICardPadding = 20.dp
        val OneUIListItemPadding = 16.dp
        val OneUIButtonPadding = 16.dp
        val OneUIDialogPadding = 24.dp
    }
    
    // OneUI 8.5 Corner Radius System
    object CornerRadius {
        val None = RoundedCornerShape(0.dp)
        val ExtraSmall = RoundedCornerShape(4.dp)
        val Small = RoundedCornerShape(8.dp)
        val Medium = RoundedCornerShape(12.dp)
        val Large = RoundedCornerShape(16.dp)
        val ExtraLarge = RoundedCornerShape(24.dp)
        val XXLarge = RoundedCornerShape(32.dp)
        
        // OneUI specific shapes
        val OneUICard = RoundedCornerShape(16.dp)
        val OneUIButton = RoundedCornerShape(12.dp)
        val OneUIDialog = RoundedCornerShape(20.dp)
        val OneUIBottomSheet = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    }
    
    // OneUI 8.5 Elevation System
    object Elevation {
        val Level0 = 0.dp
        val Level1 = 1.dp
        val Level2 = 3.dp
        val Level3 = 6.dp
        val Level4 = 8.dp
        val Level5 = 12.dp
        
        // OneUI specific elevations
        val OneUICard = 2.dp
        val OneUIButton = 1.dp
        val OneUIDialog = 8.dp
        val OneUIBottomSheet = 16.dp
    }
}

/**
 * OneUI 8.5 Light Color Scheme
 */
val OneUILightColorScheme = lightColorScheme(
    primary = OneUIDesignTokens.Colors.Primary,
    onPrimary = OneUIDesignTokens.Colors.OnPrimary,
    primaryContainer = OneUIDesignTokens.Colors.PrimaryVariant,
    onPrimaryContainer = OneUIDesignTokens.Colors.OnPrimary,
    secondary = OneUIDesignTokens.Colors.Secondary,
    onSecondary = OneUIDesignTokens.Colors.OnSecondary,
    secondaryContainer = OneUIDesignTokens.Colors.SecondaryVariant,
    onSecondaryContainer = OneUIDesignTokens.Colors.OnSecondary,
    tertiary = OneUIDesignTokens.Colors.OneUIGreen,
    onTertiary = Color.White,
    tertiaryContainer = OneUIDesignTokens.Colors.OneUILightGray,
    onTertiaryContainer = OneUIDesignTokens.Colors.OneUIDarkGray,
    error = OneUIDesignTokens.Colors.Error,
    onError = OneUIDesignTokens.Colors.OnError,
    errorContainer = Color(0xFFFFDAD6),
    onErrorContainer = Color(0xFF410002),
    background = OneUIDesignTokens.Colors.Background,
    onBackground = OneUIDesignTokens.Colors.OnBackground,
    surface = OneUIDesignTokens.Colors.Surface,
    onSurface = OneUIDesignTokens.Colors.OnSurface,
    surfaceVariant = OneUIDesignTokens.Colors.SurfaceVariant,
    onSurfaceVariant = OneUIDesignTokens.Colors.OnSurfaceVariant,
    outline = OneUIDesignTokens.Colors.OneUIGray,
    outlineVariant = OneUIDesignTokens.Colors.OneUILightGray,
    scrim = Color.Black,
    inverseSurface = OneUIDesignTokens.Colors.OneUIDarkGray,
    inverseOnSurface = Color.White,
    inversePrimary = Color(0xFF90CAF9),
    surfaceDim = Color(0xFFDDD7CF),
    surfaceBright = Color(0xFFFDF8F2),
    surfaceContainerLowest = Color.White,
    surfaceContainerLow = Color(0xFFF7F2EA),
    surfaceContainer = Color(0xFFF1ECE4),
    surfaceContainerHigh = Color(0xFFEBE6DE),
    surfaceContainerHighest = Color(0xFFE6E0D9)
)

/**
 * OneUI 8.5 Dark Color Scheme
 */
val OneUIDarkColorScheme = darkColorScheme(
    primary = Color(0xFF90CAF9),
    onPrimary = Color(0xFF003258),
    primaryContainer = Color(0xFF004A77),
    onPrimaryContainer = Color(0xFFCAE6FF),
    secondary = Color(0xFF69F0AE),
    onSecondary = Color(0xFF003919),
    secondaryContainer = Color(0xFF005227),
    onSecondaryContainer = Color(0xFF8CF7C8),
    tertiary = Color(0xFF69F0AE),
    onTertiary = Color(0xFF003919),
    tertiaryContainer = OneUIDesignTokens.Colors.DarkSurfaceVariant,
    onTertiaryContainer = OneUIDesignTokens.Colors.DarkOnSurface,
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    errorContainer = Color(0xFF93000A),
    onErrorContainer = Color(0xFFFFDAD6),
    background = OneUIDesignTokens.Colors.DarkBackground,
    onBackground = OneUIDesignTokens.Colors.DarkOnBackground,
    surface = OneUIDesignTokens.Colors.DarkSurface,
    onSurface = OneUIDesignTokens.Colors.DarkOnSurface,
    surfaceVariant = OneUIDesignTokens.Colors.DarkSurfaceVariant,
    onSurfaceVariant = Color(0xFFC4C7C5),
    outline = Color(0xFF8E918F),
    outlineVariant = Color(0xFF44474E),
    scrim = Color.Black,
    inverseSurface = Color(0xFFE6E0D9),
    inverseOnSurface = Color(0xFF1A1C18),
    inversePrimary = OneUIDesignTokens.Colors.Primary,
    surfaceDim = OneUIDesignTokens.Colors.DarkSurface,
    surfaceBright = Color(0xFF3B3F3A),
    surfaceContainerLowest = Color(0xFF0B0F0A),
    surfaceContainerLow = Color(0xFF1A1C18),
    surfaceContainer = Color(0xFF1E201C),
    surfaceContainerHigh = Color(0xFF282B26),
    surfaceContainerHighest = Color(0xFF333530)
)

/**
 * OneUI 8.5 Typography
 */
val OneUITypography = Typography(
    displayLarge = OneUIDesignTokens.Typography.DisplayLarge,
    displayMedium = OneUIDesignTokens.Typography.DisplayMedium,
    displaySmall = OneUIDesignTokens.Typography.DisplaySmall,
    headlineLarge = OneUIDesignTokens.Typography.HeadlineLarge,
    headlineMedium = OneUIDesignTokens.Typography.HeadlineMedium,
    headlineSmall = OneUIDesignTokens.Typography.HeadlineSmall,
    titleLarge = OneUIDesignTokens.Typography.TitleLarge,
    titleMedium = OneUIDesignTokens.Typography.TitleMedium,
    titleSmall = OneUIDesignTokens.Typography.TitleSmall,
    bodyLarge = OneUIDesignTokens.Typography.BodyLarge,
    bodyMedium = OneUIDesignTokens.Typography.BodyMedium,
    bodySmall = OneUIDesignTokens.Typography.BodySmall,
    labelLarge = OneUIDesignTokens.Typography.LabelLarge,
    labelMedium = OneUIDesignTokens.Typography.LabelMedium,
    labelSmall = OneUIDesignTokens.Typography.LabelSmall
)

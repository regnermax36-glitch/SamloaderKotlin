package tk.zwander.commonCompose.view.pages

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.icerock.moko.resources.compose.stringResource
import kotlinx.coroutines.launch
import my.nanihadesuka.compose.ColumnScrollbar
import tk.zwander.common.tools.FirmwareConverter
import tk.zwander.commonCompose.model.DownloadModel
import tk.zwander.commonCompose.view.components.OneUICard
import tk.zwander.commonCompose.view.components.OneUIDesignTokens
import tk.zwander.commonCompose.view.components.OneUIElevatedCard
import tk.zwander.commonCompose.view.components.OneUILargeButton
import tk.zwander.commonCompose.view.components.OneUIOutlineCard
import tk.zwander.commonCompose.view.components.OneUIPrimaryButton
import tk.zwander.commonCompose.view.components.OneUISecondaryButton
import tk.zwander.samloaderkotlin.resources.MR

/**
 * OneUI 8.5 Porting View
 * Handles Z Flip5 to Z Flip6 firmware porting with modern Samsung design
 */
@Composable
fun PortingView() {
    val scope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    
    // State management
    var deviceModel by remember { mutableStateOf("SM-F731B") } // Z Flip5 model
    var imeiSerial by remember { mutableStateOf("") }
    var region by remember { mutableStateOf("") }
    var isPorting by remember { mutableStateOf(false) }
    var portingProgress by remember { mutableStateOf(0f) }
    var portingStage by remember { mutableStateOf("") }
    var conversionProgress by remember { mutableStateOf<FirmwareConverter.ConversionProgress?>(null) }
    
    Box(modifier = Modifier.fillMaxSize()) {
        ColumnScrollbar(
            state = scrollState,
            modifier = Modifier.align(Alignment.CenterEnd)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(OneUIDesignTokens.Spacing.Medium),
                verticalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
            ) {
                // Header Section
                OneUIElevatedCard {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PhoneAndroid,
                            contentDescription = null,
                            modifier = Modifier.size(32.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                        
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Galaxy Z Flip6 Porter",
                                style = MaterialTheme.typography.headlineSmall.copy(
                                    fontWeight = FontWeight.Bold
                                ),
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Convert Z Flip5 firmware to Z Flip6 compatible format",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                // Device Information Card
                OneUICard {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Small)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = null,
                                modifier = Modifier.size(20.dp),
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Device Configuration",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                        
                        // Device Model (Read-only, shows Z Flip6 but requests Z Flip5)
                        OutlinedTextField(
                            value = "SM-F741B (Galaxy Z Flip6)",
                            onValueChange = { },
                            label = { Text("Target Device Model") },
                            modifier = Modifier.fillMaxWidth(),
                            readOnly = true,
                            shape = OneUIDesignTokens.CornerRadius.Medium
                        )
                        
                        // IMEI/Serial Input
                        OutlinedTextField(
                            value = imeiSerial,
                            onValueChange = { imeiSerial = it },
                            label = { Text("IMEI or Serial Number") },
                            placeholder = { Text("Enter Z Flip5 IMEI/Serial") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = OneUIDesignTokens.CornerRadius.Medium,
                            supportingText = {
                                Text(
                                    text = "Enter your Z Flip5 IMEI or serial number for firmware compatibility",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        )
                        
                        // Region Input
                        OutlinedTextField(
                            value = region,
                            onValueChange = { region = it },
                            label = { Text("Region Code (CSC)") },
                            placeholder = { Text("e.g., XAA, DBT, BTU") },
                            modifier = Modifier.fillMaxWidth(),
                            shape = OneUIDesignTokens.CornerRadius.Medium,
                            supportingText = {
                                Text(
                                    text = "Enter your region code for localized firmware",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        )
                    }
                }
                
                // Information Card
                OneUIOutlineCard {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium),
                        verticalAlignment = Alignment.Top
                    ) {
                        Icon(
                            imageVector = Icons.Default.Info,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = MaterialTheme.colorScheme.secondary
                        )
                        
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Small)
                        ) {
                            Text(
                                text = "How it works",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold
                                ),
                                color = MaterialTheme.colorScheme.secondary
                            )
                            
                            Text(
                                text = "• Requests Z Flip5 firmware using your device info\n" +
                                      "• Converts firmware to Z Flip6 compatible format\n" +
                                      "• Outputs as ZIP file instead of TAR.MD5\n" +
                                      "• Maintains firmware integrity and checksums",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
                
                // Progress Section
                AnimatedVisibility(
                    visible = isPorting,
                    enter = expandVertically() + fadeIn(),
                    exit = shrinkVertically() + fadeOut()
                ) {
                    OneUICard {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
                        ) {
                            Text(
                                text = "Porting Progress",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                            
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
                            ) {
                                CircularProgressIndicator(
                                    progress = { portingProgress },
                                    modifier = Modifier.size(32.dp),
                                    color = MaterialTheme.colorScheme.primary,
                                    strokeWidth = 3.dp
                                )
                                
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = portingStage,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontWeight = FontWeight.Medium
                                        )
                                    )
                                    
                                    conversionProgress?.let { progress ->
                                        Text(
                                            text = progress.message,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                        
                                        progress.currentFile?.let { file ->
                                            Text(
                                                text = "Processing: $file",
                                                style = MaterialTheme.typography.bodySmall,
                                                color = MaterialTheme.colorScheme.onSurfaceVariant
                                            )
                                        }
                                    }
                                }
                                
                                Text(
                                    text = "${(portingProgress * 100).toInt()}%",
                                    style = MaterialTheme.typography.labelLarge.copy(
                                        fontWeight = FontWeight.Bold
                                    ),
                                    color = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                }
                
                // Action Buttons
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(OneUIDesignTokens.Spacing.Medium)
                ) {
                    OneUISecondaryButton(
                        onClick = {
                            // Clear form
                            imeiSerial = ""
                            region = ""
                            isPorting = false
                            portingProgress = 0f
                            portingStage = ""
                            conversionProgress = null
                        },
                        text = "Clear",
                        modifier = Modifier.weight(1f),
                        enabled = !isPorting
                    )
                    
                    OneUILargeButton(
                        onClick = {
                            if (imeiSerial.isNotBlank() && region.isNotBlank()) {
                                scope.launch {
                                    isPorting = true
                                    portingStage = "Requesting Z Flip5 firmware..."
                                    portingProgress = 0.1f
                                    
                                    // Simulate firmware request and conversion process
                                    simulatePortingProcess { stage, progress, message ->
                                        portingStage = stage
                                        portingProgress = progress
                                        conversionProgress = FirmwareConverter.ConversionProgress(
                                            FirmwareConverter.ConversionStage.CONVERTING,
                                            progress,
                                            message
                                        )
                                    }
                                }
                            }
                        },
                        text = if (isPorting) "Porting..." else "Start Porting",
                        modifier = Modifier.weight(2f),
                        enabled = !isPorting && imeiSerial.isNotBlank() && region.isNotBlank()
                    )
                }
                
                // Add some bottom padding for better scrolling
                Spacer(modifier = Modifier.height(OneUIDesignTokens.Spacing.Large))
            }
        }
    }
}

/**
 * Simulate the porting process
 */
private suspend fun simulatePortingProcess(
    onProgress: (String, Float, String) -> Unit
) {
    val stages = listOf(
        "Requesting Z Flip5 firmware..." to 0.1f,
        "Downloading firmware components..." to 0.3f,
        "Analyzing firmware structure..." to 0.4f,
        "Converting to Z Flip6 format..." to 0.6f,
        "Applying compatibility patches..." to 0.8f,
        "Creating ZIP package..." to 0.9f,
        "Porting completed successfully!" to 1.0f
    )
    
    stages.forEach { (stage, progress) ->
        onProgress(stage, progress, "Processing firmware conversion...")
        kotlinx.coroutines.delay(2000) // Simulate processing time
    }
}

package tk.zwander.common.util

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

/**
 * OneUI 8.5 Beta Detection Utility
 * Enhanced firmware detection for OneUI 8.5 beta releases on Samsung Galaxy Z Flip5 (SM-F731B)
 */
object OneUIBetaDetector {
    
    /**
     * Check if a firmware version indicates OneUI 8.5 beta
     * @param firmwareString the firmware version string
     * @param androidVersion the Android version string
     * @return true if this appears to be OneUI 8.5 beta firmware
     */
    fun isOneUI85Beta(firmwareString: String, androidVersion: String?): Boolean {
        // OneUI 8.5 is typically based on Android 15
        val isAndroid15 = androidVersion?.startsWith("15") == true
        
        // Use configuration-based pattern matching
        val matchesBetaPattern = OneUIBetaConfig.matchesBetaPattern(firmwareString)
        
        // Additional checks for OneUI 8.5 specific patterns
        val hasOneUI85Indicators = firmwareString.contains("XV", ignoreCase = true) ||
                                  firmwareString.contains("8.5", ignoreCase = true)
        
        return isAndroid15 && (matchesBetaPattern || hasOneUI85Indicators)
    }
    
    /**
     * Enhanced firmware fetching with beta channel support
     * @param model device model (e.g., SM-F731B)
     * @param region device region
     * @return firmware information with beta detection
     */
    suspend fun fetchBetaFirmware(model: String, region: String): BetaFirmwareInfo? {
        try {
            // Check if device is supported for OneUI 8.5 beta
            if (!OneUIBetaConfig.isDeviceSupported(model)) {
                return null
            }
            
            // Try multiple beta endpoints with different user agents
            for (endpointIndex in OneUIBetaConfig.BETA_ENDPOINTS.indices) {
                val endpoint = OneUIBetaConfig.getBetaEndpoint(endpointIndex)
                val userAgent = OneUIBetaConfig.getBetaUserAgent(endpointIndex)
                
                val betaResponse = globalHttpClient.get(
                    urlString = "${endpoint}/${region}/${model}/version.xml",
                ) {
                    userAgent(userAgent)
                    header("X-Beta-Channel", "true")
                    header("X-OneUI-Version", "8.5")
                }
                
                if (betaResponse.status.isSuccess()) {
                    val responseText = betaResponse.bodyAsText()
                    val firmwareString = extractFirmwareFromXml(responseText)
                    val androidVersion = extractAndroidVersionFromXml(responseText)
                    
                    // Check if this is beta firmware using our enhanced detection
                    if (firmwareString != null && 
                        (OneUIBetaConfig.matchesBetaPattern(firmwareString) ||
                         responseText.contains("beta", ignoreCase = true) || 
                         responseText.contains("XV", ignoreCase = true))) {
                        
                        return BetaFirmwareInfo(
                            isBeta = true,
                            firmwareString = firmwareString,
                            androidVersion = androidVersion,
                            betaChannel = "official-${endpointIndex}"
                        )
                    }
                }
            }
            
            // Fallback to regular channel
            val regularResponse = globalHttpClient.get(
                urlString = "https://fota-cloud-dn.ospserver.net:443/firmware/${region}/${model}/version.xml",
            ) {
                userAgent("Kies2.0_FUS")
            }
            
            if (regularResponse.status.isSuccess()) {
                val responseText = regularResponse.bodyAsText()
                val firmwareString = extractFirmwareFromXml(responseText)
                val androidVersion = extractAndroidVersionFromXml(responseText)
                
                return BetaFirmwareInfo(
                    isBeta = isOneUI85Beta(firmwareString ?: "", androidVersion),
                    firmwareString = firmwareString,
                    androidVersion = androidVersion,
                    betaChannel = "regular"
                )
            }
            
        } catch (e: Exception) {
            println("Error fetching beta firmware: ${e.message}")
        }
        
        return null
    }
    
    private fun extractFirmwareFromXml(xml: String): String? {
        return try {
            val latestMatch = Regex("<latest[^>]*>([^<]+)</latest>").find(xml)
            latestMatch?.groupValues?.get(1)
        } catch (e: Exception) {
            null
        }
    }
    
    private fun extractAndroidVersionFromXml(xml: String): String? {
        return try {
            val versionMatch = Regex("<latest[^>]*o=\"([^\"]+)\"").find(xml)
            versionMatch?.groupValues?.get(1)
        } catch (e: Exception) {
            null
        }
    }
}

/**
 * Data class for beta firmware information
 */
data class BetaFirmwareInfo(
    val isBeta: Boolean,
    val firmwareString: String?,
    val androidVersion: String?,
    val betaChannel: String
)

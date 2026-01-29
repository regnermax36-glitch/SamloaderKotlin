package tk.zwander.common.util

/**
 * OneUI 8.5 Beta Configuration
 * Specific settings and optimizations for OneUI 8.5 beta firmware handling
 */
object OneUIBetaConfig {
    
    /**
     * Supported devices for OneUI 8.5 beta
     */
    val SUPPORTED_DEVICES = setOf(
        "SM-F731B", // Galaxy Z Flip5 Global
        "SM-F731U", // Galaxy Z Flip5 US Unlocked
        "SM-F731N", // Galaxy Z Flip5 Korea
        "SM-F731U1", // Galaxy Z Flip5 US Carrier
        "SM-F731W"  // Galaxy Z Flip5 Canada
    )
    
    /**
     * OneUI 8.5 beta firmware patterns
     */
    val BETA_FIRMWARE_PATTERNS = listOf(
        Regex(".*XV[A-Z][0-9].*"), // OneUI 8.5 beta pattern
        Regex(".*ZV[K-N][0-9].*"), // Beta build patterns
        Regex(".*BETA.*", RegexOption.IGNORE_CASE),
        Regex(".*15\\..*XV.*") // Android 15 with XV pattern
    )
    
    /**
     * Enhanced user agents for beta firmware detection
     */
    val BETA_USER_AGENTS = listOf(
        "Kies2.0_FUS",
        "Samsung-Kies/2.6",
        "SAMSUNG_Android_SamsungKies/2.6",
        "SamsungMobileDeviceUpdater/1.0"
    )
    
    /**
     * Beta firmware endpoints
     */
    val BETA_ENDPOINTS = listOf(
        "https://fota-cloud-dn.ospserver.net:443/firmware",
        "https://fota-secure-dn.ospserver.net:443/firmware",
        "https://neofussvr.sslcs.cdngc.net/firmware"
    )
    
    /**
     * Check if a device model supports OneUI 8.5 beta
     */
    fun isDeviceSupported(model: String): Boolean {
        return SUPPORTED_DEVICES.any { model.startsWith(it) }
    }
    
    /**
     * Check if firmware string matches OneUI 8.5 beta patterns
     */
    fun matchesBetaPattern(firmwareString: String): Boolean {
        return BETA_FIRMWARE_PATTERNS.any { it.matches(firmwareString) }
    }
    
    /**
     * Get the appropriate user agent for beta firmware requests
     */
    fun getBetaUserAgent(index: Int = 0): String {
        return BETA_USER_AGENTS[index % BETA_USER_AGENTS.size]
    }
    
    /**
     * Get beta firmware endpoint
     */
    fun getBetaEndpoint(index: Int = 0): String {
        return BETA_ENDPOINTS[index % BETA_ENDPOINTS.size]
    }
}

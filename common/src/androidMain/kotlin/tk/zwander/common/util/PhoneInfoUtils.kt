@file:JvmName("PhoneInfoUtilsAndroid")
package tk.zwander.common.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.telephony.TelephonyManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.Continuation
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

@SuppressLint("MissingPermission", "NewApi")
@Composable
fun rememberPhoneInfo(): PhoneInfo? {
    var permissionContinuation by remember {
        mutableStateOf<Continuation<String?>?>(null)
    }

    val context = LocalContext.current
    val telephonyManager = remember {
        context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
    }
    val permissionRequester = rememberLauncherForActivityResult(ActivityResultContracts.RequestPermission()) { result ->
        permissionContinuation?.resume(if (result) telephonyManager.imei?.slice(0..7) else null)
        permissionContinuation = null
    }

    var tac by remember {
        mutableStateOf<String?>(null)
    }

    LaunchedEffect(null) {
        withContext(Dispatchers.IO) {
            tac = if (Build.VERSION.SDK_INT > Build.VERSION_CODES.P) {
                telephonyManager.typeAllocationCode
            } else {
                suspendCoroutine { continuation ->
                    if (context.checkCallingOrSelfPermission(android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
                        continuation.resume(telephonyManager.imei?.takeIf { it.length >= 8 }?.slice(0..7))
                    } else {
                        permissionContinuation = continuation
                        permissionRequester.launch(android.Manifest.permission.READ_PHONE_STATE)
                    }
                }
            }
        }
    }

    return remember {
        derivedStateOf {
            tac.takeIf { !it.isNullOrBlank() }?.let {
                val systemProperties = Class.forName("android.os.SystemProperties")
                val getMethod = systemProperties.getMethod("get", String::class.java)
                
                val model = getMethod.invoke(null, "ro.product.model") as String
                
                // Enhanced detection for SM-F731B (Galaxy Z Flip5) with OneUI 8.5 beta support
                val enhancedModel = when {
                    model.contains("SM-F731", ignoreCase = true) -> {
                        val oneUIVersion = try {
                            getMethod.invoke(null, "ro.build.version.oneui") as? String
                        } catch (e: Exception) {
                            null
                        }
                        
                        if (oneUIVersion?.startsWith("8.5") == true) {
                            "$model (OneUI 8.5 Beta)"
                        } else {
                            model
                        }
                    }
                    else -> model
                }
                
                PhoneInfo(
                    tac = tac,
                    model = enhancedModel,
                )
            }
        }
    }.value
}

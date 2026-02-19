package dev.faizul726.accessibilityscreenshot

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.faizul726.accessibilityscreenshot.MainActivity.Companion.instance
import androidx.core.net.toUri

private const val SOURCE_CODE_LINK = "https://github.com/faizul726/accessibility-button-as-screenshot"

class MainActivity : ComponentActivity() {
    companion object {
        var instance: MainActivity? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        instance = this
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    App(Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
private fun App(modifier: Modifier = Modifier) {
    Column(
        modifier.padding(8.dp).fillMaxSize().verticalScroll(rememberScrollState())
    ) {
        Text(
            stringResource(R.string.usage),
            style = MaterialTheme.typography.headlineMedium
        )

        Text(
            """
                1. Grant accessibility permission
                Yes, that's it
            """.trimIndent(),
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(Modifier.height(12.dp))

        Text(
            stringResource(R.string.notes),
            style = MaterialTheme.typography.titleMedium
        )

        Text(
            """
                - If it says something like "This setting is currently unavailable for your security", you will need to go app info, click the 3 dot icon and click "Allow restricted settings"
                - When enabling the accessibility service, it may say that app will get full control to device. Which maybe is possible but the app explicitly does not try to access anything. Feel free to check out the source code if you have doubts :)
                - Also, it maybe is possible to not show that warning by making the app ask for more granular set of permissions, but I don't know how to do that. PRs are accepted :D
            """.trimIndent(),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(Modifier.height(12.dp))

        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Button(onClick = {
                Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                }.also {
                    instance?.startActivity(it)
                }
            }) {
                Text(stringResource(R.string.enable_accessibility_service))
            }

            Button(onClick = {
                Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    data = Uri.fromParts("package", instance?.packageName, null)
                }.also {
                    instance?.startActivity(it)
                }
            }) {
                Text(stringResource(R.string.open_app_info))
            }

            Button(onClick = {
                Intent(Intent.ACTION_VIEW).apply {
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    data = SOURCE_CODE_LINK.toUri()
                }.also {
                    instance?.startActivity(it)
                }
            }) {
                Text(stringResource(R.string.source_code))
            }
        }

        Image(painterResource(R.drawable.screenshot_1), null, Modifier.padding(vertical = 6.dp).fillMaxWidth())
        Image(painterResource(R.drawable.screenshot_2), null, Modifier.padding(vertical = 6.dp).fillMaxWidth())
        Image(painterResource(R.drawable.screenshot_3), null, Modifier.padding(vertical = 6.dp).fillMaxWidth())
        Image(painterResource(R.drawable.screenshot_4), null, Modifier.padding(vertical = 12.dp).fillMaxWidth())
    }
}
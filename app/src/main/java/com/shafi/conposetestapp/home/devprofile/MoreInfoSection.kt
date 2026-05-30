package com.shafi.conposetestapp.home.devprofile

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.home.email
import com.shafi.conposetestapp.home.launchSocialActivity

@Composable
fun MoreInfoSection() {
    val context = LocalContext.current
    Text("More Info", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary)
    Divider(modifier = Modifier.padding(vertical = 8.dp))
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        InfoCard(
            iconContent = {
                Icon(painterResource(R.drawable.ic_github_square_brands), null,
                    modifier = Modifier.size(24.dp), tint = MaterialTheme.colorScheme.primary)
            },
            title = "Recipe App on GitHub",
            subtitle = "Tap to view the repository"
        ) { launchSocialActivity(context, "repository") }

        InfoCard(
            icon = Icons.Rounded.Email,
            title = "Contact Me",
            subtitle = "Reach me at $email"
        ) {
            val intent = Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:$email"))
            runCatching { context.startActivity(intent) }
                .onFailure { Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show() }
        }

        InfoCard(
            icon = Icons.Rounded.Settings,
            title = "Demo Settings",
            subtitle = "Coming soon…"
        ) {}
    }
}

@Composable
private fun InfoCard(
    icon: ImageVector? = null,
    iconContent: (@Composable () -> Unit)? = null,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Surface(
        shape = RoundedCornerShape(16.dp),
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = Modifier.fillMaxWidth().clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            iconContent?.invoke()
                ?: icon?.let { Icon(it, null, tint = MaterialTheme.colorScheme.primary) }
            Column {
                Text(title, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                Text(subtitle, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
        }
    }
}

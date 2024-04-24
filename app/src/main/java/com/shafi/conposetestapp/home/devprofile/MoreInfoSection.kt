package com.shafi.conposetestapp.home.devprofile

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.home.email
import com.shafi.conposetestapp.home.launchSocialActivity
import com.shafi.conposetestapp.ui.theme.typography

@SuppressLint("QueryPermissionsNeeded")
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MoreInfoSection() {
    val context = LocalContext.current
    Text(
        text = "More Info",
        style = typography.h6,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    ListItem(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_github_square_brands),
                modifier = Modifier.size(24.dp),
                contentDescription = null
            )
        },
        text = {
            Text(
                text = "Recipe App Compose github",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to checkout the repo for the project") },
        modifier = Modifier
            .clickable(onClick = { launchSocialActivity(context, "repository") })
    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Email, contentDescription = null) },
        text = {
            Text(
                text = "Contact Me",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Tap to write me about any concern or info at $email") },
        modifier = Modifier
            .clickable(onClick = {
                val intent = Intent(Intent.ACTION_SEND)
                intent.putExtra(Intent.EXTRA_EMAIL, email)
                intent.setType("message/rfc822")
                if (intent.resolveActivity(context.packageManager) != null) {
                    val chooserIntent = Intent.createChooser(intent, "Choose an email app")
                    context.startActivity(chooserIntent)
                } else {
                    Toast.makeText(context, "No email app found", Toast.LENGTH_SHORT).show()
                }
            })

    )
    ListItem(
        icon = { Icon(imageVector = Icons.Rounded.Settings, contentDescription = null) },
        text = {
            Text(
                text = "Demo Settings",
                style = typography.body1.copy(fontWeight = FontWeight.Bold)
            )
        },
        secondaryText = { Text(text = "Not included yet. coming soon..") },
        modifier = Modifier.clickable(onClick = {})
    )
}

package com.shafi.conposetestapp.home.devprofile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.home.launchSocialActivity

@Composable
fun SocialRow() {
    val context = LocalContext.current
    ElevatedCard(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            SocialButton(R.drawable.ic_github_square_brands, "GitHub") { launchSocialActivity(context, "github") }
            SocialButton(R.drawable.ic_twitter_square_brands, "Twitter") { launchSocialActivity(context, "twitter") }
            SocialButton(R.drawable.ic_linkedin_brands, "LinkedIn") { launchSocialActivity(context, "linkedin") }
        }
    }
}

@Composable
private fun SocialButton(iconRes: Int, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(iconRes),
                contentDescription = label,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(28.dp)
            )
        }
        Text(label, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Medium)
    }
}

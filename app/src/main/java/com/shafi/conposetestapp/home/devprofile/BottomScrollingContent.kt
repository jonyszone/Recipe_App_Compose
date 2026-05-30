package com.shafi.conposetestapp.home.devprofile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.R

@Composable
fun BottomScrollingContent() {
    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
        Spacer(Modifier.height(20.dp))
        SocialRow()
        Spacer(Modifier.height(24.dp))
        ProfileSection(title = "About Me", body = stringResource(R.string.about_me))
        Spacer(Modifier.height(24.dp))
        InterestsSection()
        Spacer(Modifier.height(24.dp))
        ProfileSection(title = "About Project", body = stringResource(R.string.about_project))
        Spacer(Modifier.height(24.dp))
        MoreInfoSection()
    }
}

@Composable
private fun ProfileSection(title: String, body: String) {
    Text(title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary)
    Divider(modifier = Modifier.padding(vertical = 8.dp))
    Text(body, style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onSurfaceVariant)
}

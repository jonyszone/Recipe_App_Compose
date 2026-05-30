package com.shafi.conposetestapp.home.devprofile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.ui.theme.components.InterestTag

private val interests = listOf("Android", "Compose", "Flutter", "Book Reading", "Podcasts", "Football")

@Composable
fun InterestsSection() {
    Text("My Interests", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary)
    Divider(modifier = Modifier.padding(vertical = 8.dp))
    // Manual wrap: two rows of three
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        interests.take(3).forEach { InterestTag(it) }
    }
    Spacer(Modifier.height(4.dp))
    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
        interests.drop(3).forEach { InterestTag(it) }
    }
}

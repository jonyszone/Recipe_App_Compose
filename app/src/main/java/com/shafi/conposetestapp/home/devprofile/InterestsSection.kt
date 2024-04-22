package com.shafi.conposetestapp.home.devprofile

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.ui.theme.components.InterestTag
import com.shafi.conposetestapp.ui.theme.typography

@Composable
fun InterestsSection() {
    Text(
        text = "My Interests",
        style = typography.h6,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.padding(start = 8.dp, top = 16.dp)
    )
    Divider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp))
    Row(modifier = Modifier.padding(start = 8.dp, top = 8.dp)) {
        InterestTag("Android")
        InterestTag("Compose")
        InterestTag("Flutter")
    }
    Row(modifier = Modifier.padding(start = 8.dp)) {
        InterestTag("Book Reading")
        InterestTag("Podcasts")
        InterestTag("Football")
    }
}

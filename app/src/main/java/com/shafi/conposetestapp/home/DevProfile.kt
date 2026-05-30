package com.shafi.conposetestapp.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.home.devprofile.BottomScrollingContent
import com.shafi.conposetestapp.ui.theme.RecipeScaffold

const val name = "Shafi Ul Islam"
const val email = "jonyszone@gmail.com"
const val twitterUrl = "https://www.twitter.com/jonyszone"
const val linkedInUrl = "https://www.linkedin.com//in/jonyszone/"
const val githubUrl = "https://github.com/jonyszone"
const val githubRepoUrl = "https://github.com/jonyszone"

internal fun launchSocialActivity(context: Context, socialType: String) {
    val intent = when (socialType) {
        "github" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubUrl))
        "repository" -> Intent(Intent.ACTION_VIEW, Uri.parse(githubRepoUrl))
        "linkedin" -> Intent(Intent.ACTION_VIEW, Uri.parse(linkedInUrl))
        else -> Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
    }
    context.startActivity(intent)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DevProfile(
    onSnackClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val listState = rememberLazyListState()
    // Show collapsed top bar once the hero header has scrolled past the top
    val showTopBar by remember {
        derivedStateOf { listState.firstVisibleItemIndex > 0 || listState.firstVisibleItemScrollOffset > 300 }
    }

    RecipeScaffold(
        bottomBar = {
            RecipeBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.DEVPROFILE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        topBar = {
            AnimatedVisibility(
                visible = showTopBar,
                enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
            ) {
                TopAppBar(
                    title = {
                        Text(name, fontWeight = FontWeight.SemiBold)
                    },
                    navigationIcon = {
                        Image(
                            painter = painterResource(R.drawable.p1),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .size(32.dp)
                                .clip(CircleShape)
                        )
                    },
                    actions = {
                        IconButton(onClick = {}) {
                            Icon(Icons.Default.Settings, contentDescription = "Settings")
                        }
                    },
                    colors = TopAppBarDefaults.smallTopAppBarColors(
                        containerColor = MaterialTheme.colorScheme.surface,
                        titleContentColor = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    ) { padding ->
        LazyColumn(
            state = listState,
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .semantics { testTag = "Profile Screen" },
            contentPadding = PaddingValues(bottom = 24.dp)
        ) {
            item { HeroHeader() }
            item { BottomScrollingContent() }
        }
    }
}

@Composable
private fun HeroHeader() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.primary,
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.6f)
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(20.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.p1),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape)
                    .then(
                        Modifier.background(Color.White.copy(alpha = 0.2f), CircleShape)
                    )
            )
            Spacer(Modifier.height(12.dp))
            Text(name, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold, color = Color.White)
            Text("Android Developer", style = MaterialTheme.typography.bodyMedium, color = Color.White.copy(alpha = 0.85f))
        }
    }
}

package com.shafi.conposetestapp.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.home.devprofile.BottomScrollingContent
import com.shafi.conposetestapp.home.devprofile.TopScrollingContent
import com.shafi.conposetestapp.ui.theme.RecipeScaffold
import com.shafi.conposetestapp.ui.theme.modifiers.horizontalGradientBackground

const val initialImageFloat = 170f
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

@Composable
fun DevProfile(
    onSnackClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    RecipeScaffold(
        bottomBar = {
            RecipeBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FOODJOKE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .semantics { testTag = "Profile Screen" }
        ) {
            val scrollState = rememberScrollState(0)
            TopAppBarView(scrollState.value.toFloat())
            TopBackground()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(state = scrollState)
            ) {
                Spacer(modifier = Modifier.height(100.dp))
                TopScrollingContent(scrollState)
                BottomScrollingContent()

            }

        }
    }
}

@Composable
fun TopAppBarView(scroll: Float) {
    if (scroll > initialImageFloat + 5) {
        TopAppBar(
            title = {
                androidx.compose.material3.Text(text = name)
            },
            navigationIcon = {
                Image(
                    painter = painterResource(id = R.drawable.crab),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(vertical = 4.dp, horizontal = 8.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                )
                /*                Image(
                                    painter = rememberAsyncImagePainter("https://avatars.githubusercontent.com/u/26630930?v=4"),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .padding(vertical = 4.dp, horizontal = 8.dp)
                                        .size(32.dp)
                                        .clip(CircleShape)
                                )*/
            },
            actions = {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = null,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        )
    }
}

@Composable
private fun TopBackground() {
    val gradient = listOf(
        androidx.compose.material3.MaterialTheme.colorScheme.primary,
        androidx.compose.material3.MaterialTheme.colorScheme.primary.copy(alpha = 0.7f)
    )
    Spacer(
        modifier = Modifier
            .height(150.dp)
            .fillMaxWidth()
            .horizontalGradientBackground(gradient)
    )
}

@Composable
private fun Favorite(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {


}




package com.shafi.conposetestapp.home

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Face
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.shafi.conposetestapp.R

fun NavGraphBuilder.addHomeGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    composable(HomeSections.FEED.route) { from ->
        Feed(onSnackClick = { id -> onSnackSelected(id, from) }, onNavigateToRoute, modifier)
    }
    composable(HomeSections.FAVORITE.route) { from ->
        Favorite(onSnackClick = { id -> onSnackSelected(id, from) }, onNavigateToRoute, modifier)
    }
    composable(HomeSections.DEVPROFILE.route) { from ->
        DevProfile(onSnackClick = { id -> onSnackSelected(id, from) }, onNavigateToRoute, modifier)
    }
}

enum class HomeSections(
    @StringRes val title: Int,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val route: String
) {
    FEED(R.string.home_feed, Icons.Outlined.Home, Icons.Filled.Home, "home/feed"),
    FAVORITE(R.string.favorite, Icons.Outlined.FavoriteBorder, Icons.Filled.Favorite, "home/favorite"),
    DEVPROFILE(R.string.dev_profile, Icons.Outlined.Face, Icons.Filled.Face, "home/dev_profile"),
}

@Composable
fun RecipeBottomBar(
    tabs: Array<HomeSections>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit
) {
    NavigationBar {
        tabs.forEach { section ->
            val selected = section.route == currentRoute
            NavigationBarItem(
                selected = selected,
                onClick = { navigateToRoute(section.route) },
                icon = {
                    Icon(
                        imageVector = if (selected) section.selectedIcon else section.icon,
                        contentDescription = stringResource(section.title)
                    )
                },
                label = {
                    Text(
                        text = stringResource(section.title),
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Normal
                    )
                }
            )
        }
    }
}

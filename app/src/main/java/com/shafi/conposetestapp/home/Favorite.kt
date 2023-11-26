
package com.shafi.conposetestapp.home

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.shafi.conposetestapp.ui.theme.RecipeScaffold

@Composable
fun Favorite(
    onSnackClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    RecipeScaffold(
        bottomBar = {
            RecipeBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FAVORITE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) {

    }
}

@Composable
private fun Favorite(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {


}




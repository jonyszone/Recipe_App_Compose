package com.shafi.conposetestapp

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.shafi.conposetestapp.home.HomeSections
import com.shafi.conposetestapp.home.addHomeGraph
import com.shafi.conposetestapp.navigation.MainDestinations
import com.shafi.conposetestapp.navigation.rememberRecipeNavController
import com.shafi.conposetestapp.ui.theme.ConposeTestAppTheme

@Composable
fun RecipeApp() {
    ConposeTestAppTheme {
        val recipeNavController = rememberRecipeNavController()

        NavHost(
            navController = recipeNavController.navController,
            startDestination = MainDestinations.HOME_ROUTE) {
            recipeNavGraph(
                onSnackSelected = recipeNavController::navigateToSnackDetail,
                upPress = recipeNavController::upPress,
                onNavigateToRoute = recipeNavController::navigateToBottomBarRoute
            )
        }
    }
}


private fun NavGraphBuilder.recipeNavGraph(
    onSnackSelected: (Long, NavBackStackEntry) -> Unit,
    upPress: () -> Unit,
    onNavigateToRoute: (String) -> Unit
) {
    navigation(
        route = MainDestinations.HOME_ROUTE,
        startDestination = HomeSections.FEED.route
    ) {
        addHomeGraph(onSnackSelected, onNavigateToRoute)
    }
    composable(
        "${MainDestinations.RECIPE_DETAIL_ROUTE}/{${MainDestinations.SNACK_ID_KEY}}",
        arguments = listOf(navArgument(MainDestinations.SNACK_ID_KEY) { type = NavType.LongType })
    ) { backStackEntry ->
        val arguments = requireNotNull(backStackEntry.arguments)
        val recipeId = arguments.getLong(MainDestinations.SNACK_ID_KEY)
       // SnackDetail(recipeId, upPress)
    }
}

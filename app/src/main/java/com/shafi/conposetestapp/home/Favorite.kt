package com.shafi.conposetestapp.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.shafi.conposetestapp.ui.theme.RecipeScaffold
import com.shafi.conposetestapp.viewmodel.RecipeViewModel

@Composable
fun Favorite(
    onSnackClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier,
    vm: RecipeViewModel = viewModel()
) {
    val favorites by vm.favorites.collectAsStateWithLifecycle()

    RecipeScaffold(
        bottomBar = {
            RecipeBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FAVORITE.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            Text(
                text = "My Favorites",
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp)
            )
            if (favorites.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("❤️", style = MaterialTheme.typography.displayMedium)
                        Spacer(Modifier.height(8.dp))
                        Text("No favorites yet", style = MaterialTheme.typography.titleMedium)
                        Text(
                            "Tap the heart on any recipe to save it here",
                            style = MaterialTheme.typography.bodySmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                }
            } else {
                LazyColumn(contentPadding = PaddingValues(bottom = 8.dp)) {
                    items(favorites, key = { it.id }) { recipe ->
                        RecipeCard(
                            recipe = recipe,
                            onItemClick = { onSnackClick(recipe.id.toLong()) },
                            onFavoriteClick = { vm.toggleFavorite(recipe.id) }
                        )
                    }
                }
            }
        }
    }
}

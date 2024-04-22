
package com.shafi.conposetestapp.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.shafi.conposetestapp.R
import com.shafi.conposetestapp.component.ItemFoodCard
import com.shafi.conposetestapp.model.FakeFoodData
import com.shafi.conposetestapp.model.FoodRecipe
import com.shafi.conposetestapp.ui.theme.RecipeScaffold

@Composable
fun Feed(
    onSnackClick: (Long) -> Unit,
    onNavigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    RecipeScaffold(
        bottomBar = {
            RecipeBottomBar(
                tabs = HomeSections.values(),
                currentRoute = HomeSections.FEED.route,
                navigateToRoute = onNavigateToRoute
            )
        },
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize()
                .padding(4.dp)
                .padding(it)
        ) {
            Text(
                text = "Work in progress",
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            
            Spacer(modifier = Modifier)
            Home(foodList = FakeFoodData.foodList) {

            }
        }

    }
}

@Composable
fun Home(/*navController: NavHostController,*/ foodList: List<FoodRecipe>, toggleTheme: () -> Unit) {
    LazyColumn {
        item {
           // Spacer(modifier = Modifier.height(3.dp))
        }
        items(foodList) {
            foodList.forEach {
                ItemFoodCard(
                    it,
                    onItemClicked = { food ->

                    }
                )
            }
        }
    }
}

@Composable
private fun Favorite(
    onSnackClick: (Long) -> Unit,
    modifier: Modifier = Modifier
) {


}




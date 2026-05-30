package com.shafi.conposetestapp.data

import android.content.Context
import com.shafi.conposetestapp.model.FoodRecipe

class MealDbRepository(context: Context) {

    private val api = MealDbApi.service
    private val favoritesRepo = FavoritesRepository(context)

    val favoriteIds = favoritesRepo.favoriteIds

    suspend fun toggleFavorite(id: Int) = favoritesRepo.toggleFavorite(id)

    suspend fun searchMeals(query: String): List<FoodRecipe> =
        api.searchMeals(query).meals?.map { it.toFoodRecipe() } ?: emptyList()

    suspend fun getMealById(id: Int): FoodRecipe? =
        api.getMealById(id.toString()).meals?.firstOrNull()?.toFoodRecipe()

    suspend fun getCategories(): List<String> =
        api.listCategories().categories?.map { it.name } ?: emptyList()

    suspend fun getMealsByCategory(category: String): List<FoodRecipe> =
        api.filterByCategory(category).meals?.map { it.toFoodRecipe().copy(category = category) } ?: emptyList()
}

fun Meal.toFoodRecipe() = FoodRecipe(
    id = idMeal.toIntOrNull() ?: 0,
    name = strMeal,
    imageUrl = strMealThumb ?: "",
    about = strInstructions?.take(200)?.plus("…") ?: "",
    category = strCategory ?: "",
    cookTime = "",
    servings = 2,
    ingredients = ingredients(),
    steps = steps()
)

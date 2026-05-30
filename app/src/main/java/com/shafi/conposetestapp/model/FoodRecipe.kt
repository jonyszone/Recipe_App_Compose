package com.shafi.conposetestapp.model

data class FoodRecipe(
    val id: Int,
    val name: String,
    val imageUrl: String,          // URL from API
    val about: String,
    val category: String = "",
    val cookTime: String = "",
    val servings: Int = 2,
    val ingredients: List<String> = emptyList(),
    val steps: List<String> = emptyList(),
    val isFavorite: Boolean = false
)

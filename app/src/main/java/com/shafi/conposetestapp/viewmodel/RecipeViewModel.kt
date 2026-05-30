package com.shafi.conposetestapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.shafi.conposetestapp.data.MealDbRepository
import com.shafi.conposetestapp.model.FoodRecipe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

sealed interface UiState {
    object Loading : UiState
    data class Success(val recipes: List<FoodRecipe>) : UiState
    data class Error(val message: String) : UiState
}

@OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = MealDbRepository(application)

    val searchQuery = MutableStateFlow("")

    val categories = MutableStateFlow<List<String>>(emptyList())
    val selectedCategory = MutableStateFlow("Beef")

    private val _feedState = MutableStateFlow<UiState>(UiState.Loading)
    val feedState: StateFlow<UiState> = _feedState.asStateFlow()

    // Favorite meals are fetched by id from the API (no local meal DB)
    val favorites: StateFlow<List<FoodRecipe>> = repository.favoriteIds
        .mapLatest { ids -> ids.mapNotNull { repository.getMealById(it)?.copy(isFavorite = true) } }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    private val favoriteIds: StateFlow<Set<Int>> = repository.favoriteIds
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptySet())

    init {
        loadCategories()
        viewModelScope.launch {
            combine(searchQuery.debounce(400), selectedCategory) { q, cat -> q to cat }
                .collectLatest { (query, category) ->
                    if (query.isBlank()) loadCategory(category) else searchMeals(query)
                }
        }
    }

    private fun loadCategories() {
        viewModelScope.launch {
            runCatching { repository.getCategories() }.onSuccess { categories.value = it }
        }
    }

    fun selectCategory(category: String) {
        searchQuery.value = ""
        selectedCategory.value = category
    }

    private suspend fun loadCategory(category: String) {
        _feedState.value = UiState.Loading
        runCatching { repository.getMealsByCategory(category) }
            .onSuccess { _feedState.value = UiState.Success(withFavorites(it)) }
            .onFailure { _feedState.value = UiState.Error(it.message ?: "Failed to load recipes") }
    }

    private suspend fun searchMeals(query: String) {
        _feedState.value = UiState.Loading
        runCatching { repository.searchMeals(query) }
            .onSuccess { _feedState.value = UiState.Success(withFavorites(it)) }
            .onFailure { _feedState.value = UiState.Error(it.message ?: "Search failed") }
    }

    private fun withFavorites(meals: List<FoodRecipe>): List<FoodRecipe> {
        val ids = favoriteIds.value
        return meals.map { it.copy(isFavorite = it.id in ids) }
    }

    fun toggleFavorite(id: Int) {
        viewModelScope.launch {
            repository.toggleFavorite(id)
            val current = _feedState.value
            if (current is UiState.Success) {
                _feedState.value = UiState.Success(
                    current.recipes.map { if (it.id == id) it.copy(isFavorite = !it.isFavorite) else it }
                )
            }
        }
    }

    fun retry() {
        viewModelScope.launch {
            if (searchQuery.value.isBlank()) loadCategory(selectedCategory.value)
            else searchMeals(searchQuery.value)
        }
    }

    suspend fun getMealById(id: Int): FoodRecipe? = runCatching {
        repository.getMealById(id)?.copy(isFavorite = id in favoriteIds.value)
    }.getOrNull()
}

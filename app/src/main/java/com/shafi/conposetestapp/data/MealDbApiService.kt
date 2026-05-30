package com.shafi.conposetestapp.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealDbApiService {
    @GET("search.php")
    suspend fun searchMeals(@Query("s") query: String): MealResponse

    @GET("lookup.php")
    suspend fun getMealById(@Query("i") id: String): MealResponse

    @GET("list.php?c=list")
    suspend fun listCategories(): CategoryListResponse

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): MealResponse
}

object MealDbApi {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"

    val service: MealDbApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BASIC
                    })
                    .build()
            )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MealDbApiService::class.java)
    }
}

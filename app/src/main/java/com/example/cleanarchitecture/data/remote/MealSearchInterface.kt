package com.example.cleanarchitecture.data.remote

import com.example.cleanarchitecture.data.model.DTOMeal
import com.example.cleanarchitecture.data.model.DTOMeals
import retrofit2.http.GET
import retrofit2.http.Query

interface MealSearchInterface {

    @GET("api/json/v1/1/search.php")
    suspend fun getChickens(@Query("s") s:String):DTOMeals

    @GET("v1/1/lookup.php")
    suspend fun getChickenDetails(@Query("i") i:String):DTOMeals

}
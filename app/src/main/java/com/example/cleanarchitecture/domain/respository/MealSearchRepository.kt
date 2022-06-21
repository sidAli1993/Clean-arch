package com.example.cleanarchitecture.domain.respository

import com.example.cleanarchitecture.data.model.DTOMeals

interface MealSearchRepository {

    suspend fun getMealsList(s: String):DTOMeals

}
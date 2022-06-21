package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.DTOMeals
import com.example.cleanarchitecture.data.remote.MealSearchInterface
import com.example.cleanarchitecture.domain.respository.MealSearchRepository

class GetMealListImpl(private val mealSearchInterface: MealSearchInterface) :MealSearchRepository {

    override suspend fun getMealsList(s: String): DTOMeals {
        return mealSearchInterface.getChickens(s)
    }
}
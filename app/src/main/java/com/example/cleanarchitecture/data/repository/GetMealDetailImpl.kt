package com.example.cleanarchitecture.data.repository

import com.example.cleanarchitecture.data.model.DTOMeal
import com.example.cleanarchitecture.data.model.DTOMeals
import com.example.cleanarchitecture.data.remote.MealSearchInterface
import com.example.cleanarchitecture.domain.respository.GetMealDetailsRepository

class GetMealDetailImpl(private val mealSearchInterface: MealSearchInterface):GetMealDetailsRepository {

    override suspend fun getMealDetails(s: String): DTOMeals {
       return mealSearchInterface.getChickenDetails(s)
    }
}
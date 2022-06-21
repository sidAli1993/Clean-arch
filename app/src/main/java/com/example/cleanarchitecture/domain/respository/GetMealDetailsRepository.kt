package com.example.cleanarchitecture.domain.respository

import com.example.cleanarchitecture.data.model.DTOMeal
import com.example.cleanarchitecture.data.model.DTOMeals

interface GetMealDetailsRepository {
    suspend fun getMealDetails(s: String): DTOMeals
}
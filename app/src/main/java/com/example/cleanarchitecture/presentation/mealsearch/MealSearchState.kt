package com.example.cleanarchitecture.presentation.mealsearch

import com.example.cleanarchitecture.domain.model.Meal

data class MealSearchState(val list: List<Meal>?=null,val error:String="",val isLoading:Boolean=false)

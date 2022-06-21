package com.example.cleanarchitecture.presentation.mealsearch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.domain.use_case.GetMealSearchListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val getMealSearchListUseCase: GetMealSearchListUseCase) :
    ViewModel() {
    private val _mealSearchList = MutableStateFlow<MealSearchState>(MealSearchState())
    val mealsearchlist: StateFlow<MealSearchState> = _mealSearchList

    fun searchMealList(s: String) {
        getMealSearchListUseCase(s).onEach {
            when (it) {
                is Resource.Loading -> {
                    _mealSearchList.value = MealSearchState(isLoading = true)
                }
                is Resource.Error -> {
                    _mealSearchList.value = MealSearchState(error = it.message ?: "")
                }
                is Resource.Success -> {
                    _mealSearchList.value = MealSearchState(list = it.data)
                }
            }
        }.launchIn(viewModelScope)
    }
}
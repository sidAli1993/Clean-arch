package com.example.cleanarchitecture.domain.use_case

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.model.getBasic
import com.example.cleanarchitecture.domain.model.Meal
import com.example.cleanarchitecture.domain.respository.MealSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealSearchListUseCase @Inject constructor(private val mealSearchRepository: MealSearchRepository) {

    operator fun invoke(s: String): Flow<Resource<List<Meal>>> = flow {
        try {
            emit(Resource.Loading())

            val response = mealSearchRepository.getMealsList(s)

            val list =
                if (response.meals.isNullOrEmpty()) emptyList<Meal>() else response.meals.map {
                    it.getBasic()
                }

            emit(Resource.Success(data = list))
        } catch (e: HttpException) {
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        } catch (e: IOException) {
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        } catch (e: Exception) {
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        }
    }
}
package com.example.cleanarchitecture.domain.use_case

import com.example.cleanarchitecture.common.Resource
import com.example.cleanarchitecture.data.model.getDetails
import com.example.cleanarchitecture.domain.model.Meal
import com.example.cleanarchitecture.domain.model.MealDetails
import com.example.cleanarchitecture.domain.respository.GetMealDetailsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetMealDetailsUseCase @Inject constructor(private val getMealDetails: GetMealDetailsRepository) {
    operator fun invoke(s:String):Flow<Resource<MealDetails>> = flow {
        try {
            emit(Resource.Loading())
            val response=getMealDetails.getMealDetails(s).meals[0].getDetails()

            emit(Resource.Success(data = response))
        }catch (e:HttpException){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        }catch (e:IOException){
            emit(Resource.Error(message = e.localizedMessage?:"Check your internet connection"))
        }catch (e:Exception){
            emit(Resource.Error(message = e.localizedMessage?:"Unknown error"))
        }
    }

}
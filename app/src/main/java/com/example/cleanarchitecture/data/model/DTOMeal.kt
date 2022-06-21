package com.example.cleanarchitecture.data.model

import com.example.cleanarchitecture.domain.model.Meal
import com.example.cleanarchitecture.domain.model.MealDetails

data class DTOMeal(
    val dateModified: Any?,
    val idMeal: String?,
    val strArea: String?,
    val strCategory: String?,
    val strCreativeCommonsConfirmed: Any?,
    val strDrinkAlternate: Any?,
    val strImageSource: Any?,
    val strIngredient1: String?,
    val strIngredient10: String?,
    val strIngredient11: String?,
    val strIngredient12: String?,
    val strIngredient13: String?,
    val strIngredient14: String?,
    val strIngredient15: String?,
    val strIngredient16: Any?,
    val strIngredient17: Any?,
    val strIngredient18: Any?,
    val strIngredient19: Any?,
    val strIngredient2: String?,
    val strIngredient20: Any?,
    val strIngredient3: String?,
    val strIngredient4: String?,
    val strIngredient5: String?,
    val strIngredient6: String?,
    val strIngredient7: String?,
    val strIngredient8: String?,
    val strIngredient9: String?,
    val strInstructions: String?,
    val strMeal: String?,
    val strMealThumb: String?,
    val strMeasure1: String?,
    val strMeasure10: String?,
    val strMeasure11: String?,
    val strMeasure12: String?,
    val strMeasure13: String?,
    val strMeasure14: String?,
    val strMeasure15: String?,
    val strMeasure16: Any?,
    val strMeasure17: Any?,
    val strMeasure18: Any?,
    val strMeasure19: Any?,
    val strMeasure2: String?,
    val strMeasure20: Any?,
    val strMeasure3: String?,
    val strMeasure4: String?,
    val strMeasure5: String?,
    val strMeasure6: String?,
    val strMeasure7: String?,
    val strMeasure8: String?,
    val strMeasure9: String?,
    val strSource: Any?,
    val strTags: String?,
    val strYoutube: String?
)

fun DTOMeal.getBasic():Meal{
    return Meal(
        id = this.idMeal?:"",
        chickenName = this.strMeal?:"",
        image = this.strMealThumb?:""
    )

}
fun DTOMeal.getDetails():MealDetails{
    return MealDetails(
        id = this.idMeal?:"",
        name = this.strMeal?:"",
        thumbImg=this.strMealThumb?:""

    )
}
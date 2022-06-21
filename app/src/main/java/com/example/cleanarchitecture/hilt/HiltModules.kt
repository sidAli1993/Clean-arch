package com.example.cleanarchitecture.hilt

import com.example.cleanarchitecture.BuildConfig
import com.example.cleanarchitecture.common.Constants
import com.example.cleanarchitecture.data.remote.MealSearchInterface
import com.example.cleanarchitecture.data.repository.GetMealDetailImpl
import com.example.cleanarchitecture.data.repository.GetMealListImpl
import com.example.cleanarchitecture.domain.respository.GetMealDetailsRepository
import com.example.cleanarchitecture.domain.respository.MealSearchRepository
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltModules {

    @Provides
    @Singleton
    fun provideMealSearchAPI():MealSearchInterface{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            httpClient.addInterceptor(logging)
        }
        val gson = GsonBuilder()
            .serializeNulls()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
        httpClient.addInterceptor(Interceptor { chain ->
            val original: Request = chain.request()
            val originalHttpUrl: HttpUrl = original.url
            val url = originalHttpUrl.newBuilder()
                .build()
            val requestBuilder: Request.Builder = original.newBuilder()
                .url(url)
            val request: Request = requestBuilder.build()
            chain.proceed(request)
        })
        httpClient.readTimeout(120, TimeUnit.SECONDS)
            .connectTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(120, TimeUnit.SECONDS)
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build().create(MealSearchInterface::class.java)
    }

    @Provides
    fun provideMealSearchRepository(mealSearchInterface: MealSearchInterface):MealSearchRepository{
        return GetMealListImpl(mealSearchInterface)
    }

    @Provides
    fun provideMealDetailRepository(mealSearchInterface: MealSearchInterface):GetMealDetailsRepository{
        return GetMealDetailImpl(mealSearchInterface)
    }

}
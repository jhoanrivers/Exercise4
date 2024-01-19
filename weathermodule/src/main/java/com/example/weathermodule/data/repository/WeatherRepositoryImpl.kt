package com.example.weathermodule.data.repository

import com.example.weathermodule.data.model.WeatherResponse
import com.example.weathermodule.data.service.ApiService
import retrofit2.Response

class WeatherRepositoryImpl (private val apiService: ApiService) : WeatherRepository {


    override suspend fun getForecastWeather(cityId: String, appId: String) : Response<WeatherResponse> {
        return apiService.getForecast(cityId, appId)
    }
}
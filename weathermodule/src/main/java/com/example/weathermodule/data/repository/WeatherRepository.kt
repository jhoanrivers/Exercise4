package com.example.weathermodule.data.repository

import com.example.weathermodule.data.model.WeatherResponse
import retrofit2.Response

interface WeatherRepository {


    suspend fun getForecastWeather(cityId: String, appId: String) : Response<WeatherResponse>

}
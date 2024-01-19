package com.example.weathermodule.data.service

import com.example.weathermodule.data.model.WeatherResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("data/2.5/forecast")
    suspend fun getForecast(
        @Query("id") id: String,
        @Query("appid") appId: String): Response<WeatherResponse>



    //suspend fun getCurrent(id: String, appId: String) : Response<>


}



package com.example.weathermodule.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(

    @SerializedName("cod")
    val cod: String,

    @SerializedName("message")
    val message: Int,

    @SerializedName("city")
    val city: CityEntity?,


    @SerializedName("list")
    val listWeather:List<WeatherEntity>?,

    )


data class CityEntity (

    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,
)


data class WeatherEntity(

    @SerializedName("main")
    val mainWeather: MainWeatherEntity,


    @SerializedName("weather")
    val dataWeather: List<DataWeatherEntity>


)

data class MainWeatherEntity(

    @SerializedName("temp")
    val temp: Double,


    @SerializedName("temp_min")
    val tempMin: Double,


    @SerializedName("temp_max")
    val tempMax: Double

)

data class DataWeatherEntity(


    @SerializedName("main")
    val main: String,


    @SerializedName("icon")
    val icon: String
)
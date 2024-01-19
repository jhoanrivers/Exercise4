package com.example.exercise4.ui.pages.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermodule.data.model.WeatherResponse
import com.example.weathermodule.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel(){

    val listWeather = MutableLiveData<WeatherResponse>()


    var cityId = "4899170"
    var appId = "1b7eeecd2ff64dc83e8dcf1f4cb2102b"



    fun getForecastWeather() {
        viewModelScope.launch {
            try {
                val response = weatherRepository.getForecastWeather(cityId, appId)
                if(response.isSuccessful){
                    listWeather.postValue(response.body())
                } else{
                    throw Exception("Failed to get weather")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}
package com.example.exercise4.ui.pages.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weathermodule.data.model.WeatherEntity
import com.example.weathermodule.data.model.WeatherResponse
import com.example.weathermodule.data.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject


@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) : ViewModel(){

    val listWeather = MutableLiveData<WeatherResponse>()
    val loadingData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<String>()

    val mapWeatherData = MutableLiveData<Map<String, Int>>()
    val mapWeather = mutableMapOf<String, Int>()

    val listCity = listOf(
        "4899170",
        "6244895",
        "2661039",
        "5879092",
        "6198431",
        "5780908",
        "5781070",
        "792680",
        "2662148",
        "4350049",
    )
    var appId = "1b7eeecd2ff64dc83e8dcf1f4cb2102b"



    fun getForecastWeather() {
        viewModelScope.launch {
            try {
                val deferredResult = listCity.map {
                    async { weatherRepository.getForecastWeather(it,appId) }
                }

                val result = deferredResult.awaitAll()
                handleCombinedResult(result)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun handleCombinedResult(result: List<Response<WeatherResponse>>) {

        for(response in result){
            if(response.isSuccessful){
                for(item in response.body()?.listWeather!!){
                    var key = item.dataWeather.first().main
                    if(mapWeather.containsKey(key)){
                        var value = mapWeather.getValue(key)
                        value++
                        mapWeather.put(key, value)
                    } else{
                        mapWeather.putIfAbsent(key, 1)
                    }
                }
            }
        }

    }


}
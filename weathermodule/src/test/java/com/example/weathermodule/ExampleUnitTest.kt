package com.example.weathermodule

import com.example.weathermodule.data.model.WeatherResponse
import com.example.weathermodule.data.repository.WeatherRepository
import com.example.weathermodule.data.repository.WeatherRepositoryImpl
import com.example.weathermodule.data.service.ApiService
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import retrofit2.Retrofit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */


@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    lateinit var apiService: ApiService

    lateinit var weatherRepository: WeatherRepository


    @Before
    fun setup() {
        weatherRepository = WeatherRepositoryImpl(apiService)
    }

    @Test
    fun addition_isCorrect() = runBlocking{


        val response: Response<WeatherResponse> = Response.success(WeatherResponse("cod", 1, null, null))
        `when`(weatherRepository.getForecastWeather("123","123")).thenReturn(response)

        val result = weatherRepository.getForecastWeather("123","123")

        assertEquals(response.code(), result.code())
        assertEquals(response.message(), result.message())
        assertEquals(response.body(), result.body())
        assertEquals(response.isSuccessful, result.isSuccessful)

    }
}
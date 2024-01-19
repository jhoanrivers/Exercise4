package com.example.exercise4.ui.pages.weather

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.exercise4.R
import com.example.exercise4.ui.pages.BaseActivity
import dagger.hilt.android.HiltAndroidApp


class WeatherActivity : BaseActivity() {


    private val weatherViewModel by viewModels<WeatherViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        initView()
        bindViewModel()
        bindViewEvents()
    }


    fun initView() {
        println("run this code")
        weatherViewModel.getForecastWeather()
    }

    fun bindViewModel() {
        weatherViewModel.listWeather.observe(this){
            println(it.city.name)
        }
    }

    fun bindViewEvents() {
    }
}
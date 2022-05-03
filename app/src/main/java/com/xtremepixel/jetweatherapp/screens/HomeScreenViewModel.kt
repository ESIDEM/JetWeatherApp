package com.xtremepixel.jetweatherapp.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repo: WeatherRepository) : ViewModel() {

    suspend fun getWeatherData(city: String): ResultWrapper<Weather, Boolean, Exception>{

        return  repo.getWeather(city = city)
    }
}
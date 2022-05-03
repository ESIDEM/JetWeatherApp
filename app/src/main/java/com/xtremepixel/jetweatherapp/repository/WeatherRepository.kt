package com.xtremepixel.jetweatherapp.repository

import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.network.WeatherApi
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather( city: String): ResultWrapper<Weather, Boolean, Exception> {

        val response = try {

            api.getWeather(query = city)
        } catch (e: Exception) {
            return ResultWrapper(error = e)
        }

        return ResultWrapper(data = response)
    }
}